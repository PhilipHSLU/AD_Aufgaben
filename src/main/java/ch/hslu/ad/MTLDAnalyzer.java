package ch.hslu.ad;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

/**
 * Analyzes and calculates the Measure of Textual Lexical Diversity (MTLD)
 * for each book contained in the provided file 'books.xml'.
 */
public class MTLDAnalyzer {
    private static final double TTR_THRESHOLD = 0.72; // Standard MTLD threshold
    private static final int MIN_TOKENS = 50;

    /**
     * Simple tree structure to represent an XML document containing books, book, chapter, paragraphs.
     * The structure does not model XML attributes.
     */
    private static class BookNode {
        final String name;
        final String text;
        final List<BookNode> children;

        BookNode(String name, String text, List<BookNode> children) {
            this.name = name;
            this.text = text;
            this.children = children;
        }

        /**
         * Creates the tree structure from an XML document.
         */
        static BookNode getFromXml(String name) {
            Document doc;
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                doc = builder.parse(name);
                doc.getDocumentElement().normalize();
                Node books = doc.getElementsByTagName("books").item(0);
                return getFromXml(books);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                // forward checked exception to caller in case
                // file is not well-formed or not readable
                throw new RuntimeException(e);
            }
        }

        static BookNode getFromXml(Node node) {
            if (node == null) throw new IllegalArgumentException("node must not be null");

            List<BookNode> bookNodes = new ArrayList<>();
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); ++i) {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    bookNodes.add(getFromXml(childNode));
                }
            }
            return new BookNode(node.getNodeName(), node.getTextContent(), bookNodes);
        }
    }

    /**
     * Class to calculate the cumulative (running) average.
     */
    private static class CumulativeAverage {
        private double sum;
        private int count;

        public void add(double value) {
            if (value > 0.0) {
                sum += value;
                ++count;
            }
        }

        public void add(CumulativeAverage other) {
            sum += other.sum;
            count += other.count;
        }

        public double getAverage() {
            if (count == 0) throw new IllegalStateException("No values added");
            return sum / count;
        }
    }

    /**
     * Main function that calculates the Measure of Textual Lexical Diversity (MTLD).
     */
    private double calculateMTLD(String paragraph) {
        String[] tokens = paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        if (tokens.length < MIN_TOKENS) return 0.0;
        double forward = calculateMTLDSinglePass(tokens);
        List<String> list = Arrays.asList(tokens);
        Collections.reverse(list);
        double backward = calculateMTLDSinglePass(list.toArray(new String[0]));
        return (forward + backward) / 2.0;
    }

    private double calculateMTLDSinglePass(String[] words) {
        int factorCount = 0;
        int currentWordCount = 0;
        Set<String> uniqueWords = new HashSet<>();

        for (String token : words) {
            if (token.isEmpty()) continue;

            uniqueWords.add(token);
            currentWordCount++;

            double currentTTR = (double) uniqueWords.size() / currentWordCount;
            if (currentTTR < TTR_THRESHOLD) {
                factorCount++;
                uniqueWords.clear();
                currentWordCount = 0;
            }
        }

        // adds final partial factor
        double remainingTTR = (double) uniqueWords.size() / Math.max(1, currentWordCount);
        double partialFactor = (1.0 - remainingTTR) / (1.0 - TTR_THRESHOLD);
        double totalFactors = factorCount + partialFactor;
        return words.length / Math.max(1, totalFactors);
    }

    /**
     * Calculates the MTLD for all paragraphs of a book and prints
     * the average for each book.
     */
    private CumulativeAverage analyzeBook(BookNode node) {
        if (node == null) throw new IllegalArgumentException("node must not be null");

        CumulativeAverage cumulativeAverage = new CumulativeAverage();
        if (node.name.equals("paragraph")) {
            cumulativeAverage.add(calculateMTLD(node.text));
        } else {
            for (BookNode bookNode : node.children) {
                cumulativeAverage.add(analyzeBook(bookNode));
            }
        }
        if (node.name.equals("book")) {
            System.out.printf("Book '%s' has avg. MTLD of %.2f\n", getTitle(node), cumulativeAverage.getAverage());
        }
        return cumulativeAverage;
    }

    private String getTitle(BookNode node) {
        if (node == null) throw new IllegalArgumentException("node must not be null");
        if (!node.name.equals("book")) throw new IllegalArgumentException("node must be a book node");

        for (BookNode bookNode : node.children) {
            if (bookNode.name.equals("title")) {
                return bookNode.text;
            }
        }
        return ""; // no title found
    }

    public static void main(String[] args) throws IOException {
        BookNode books = BookNode.getFromXml("books.xml");
        MTLDAnalyzer mtldAnalyzer = new MTLDAnalyzer();

        // note: consider the first three executions as warm-up
        for (int i = 0; i < 10; ++i) {
            long timeBefore = System.nanoTime();

            mtldAnalyzer.analyzeBook(books);

            System.out.println("Time: " + (System.nanoTime() - timeBefore) / 1000000);
        }
    }
}
