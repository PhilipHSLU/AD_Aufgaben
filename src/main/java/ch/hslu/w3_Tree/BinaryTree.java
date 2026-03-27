package ch.hslu.w3_Tree;

public class BinaryTree {
    // --- BAUMKLASSE (BinarySearchTree) ---
public class BinarySearchTree {
    Node root; // Einstiegspunkt des Baumes
    // Konstruktor: Baut den statischen Baum für Aufgabe 5.b und 5.c
    public BinarySearchTree() {
        // Wir erstellen 10 eindeutige Knoten.
        // Wurzel ist 10. Links davon alles < 10, rechts alles > 10.
        root = new Node(10);

        // Linker Teilbaum
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(8);

        // Rechter Teilbaum
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);
        root.right.right.right = new Node(20);

        System.out.println("Statischer Baum initialisiert.");
    }
    // --- AUFGABE 5.d: Suchen (search) ---
    // Sucht ein Element im Baum und gibt true/false zurück.
    public boolean search(int target) {
        System.out.println("-> Starte Suche nach: " + target);
        return searchRecursive(root, target);
    }
    private boolean searchRecursive(Node current, int target) {
        // Abbruchbedingung 1: Wir sind an einem leeren Ende angekommen (nicht gefunden)
        if (current == null) {
            System.out.println("   Ende erreicht. " + target + " nicht gefunden.");
            return false;
        }

        System.out.println("   Besuche: " + current);
// Abbruchbedingung 2: Element gefunden!
        if (target == current.data) {
            System.out.println("   Gefunden!");
            return true;
        }
// Rekursion: Wenn der Zielwert kleiner ist, gehe nach LINKS. Sonst nach RECHTS.
        if (target < current.data) {
            System.out.println("   " + target + " < " + current.data + " -> gehe links.");
            return searchRecursive(current.left, target);
        } else {
            System.out.println("   " + target + " > " + current.data + " -> gehe rechts.");
            return searchRecursive(current.right, target);
        }
    }
    // --- AUFGABE 5.e: Einfügen (insert) ---
    // Fügt ein neues Element an der korrekten Position ein.
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    private Node insertRecursive(Node current, int value) {
        // Wenn wir eine leere Stelle finden, erstellen wir hier den neuen Knoten.
        if (current == null) {
            return new Node(value);
        }
// Baum durchlaufen, um die richtige leere Stelle zu finden
        if (value < current.data) {
            // Gehe links und aktualisiere die linke Referenz
            current.left = insertRecursive(current.left, value);
        } else if (value > current.data) {
            // Gehe rechts und aktualisiere die rechte Referenz
            current.right = insertRecursive(current.right, value);
        } else {
            // Wert existiert bereits (value == current.data) -> Nichts tun, da keine Duplikate erlaubt sind.
            System.out.println("Wert " + value + " existiert bereits.");
        }
        return current; // Gibt den aktuellen Knoten (unverändert) auf dem Rückweg nach oben zurück
    }
    // --- AUFGABE 5.f: Traversieren (inorder) ---
    // Durchläuft den Baum in der Reihenfolge: Links -> Aktuell -> Rechts.
    // Das führt bei einem binären Suchbaum zu einer aufsteigend sortierten Ausgabe.
    public void inorder() {
        System.out.print("Inorder Traversierung: ");
        inorderRecursive(root);
        System.out.println();
    }
    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);       // 1. Alles Linke besuchen (kleinere)
            System.out.print(node.data + " "); // 2. Sich selbst ausgeben
            inorderRecursive(node.right);      // 3. Alles Rechte besuchen (grössere)
        }
    }
}
}
