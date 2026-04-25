// TODO: Sie müssen den folgenden Paketnamen an Ihre Projektstruktur anpassen
package ch.hslu.w8_Sortieren.afg3and4and5;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class SortingAnimation {
    // Feld für die Animation
    private int[] array;

    // Set für die Indizes, die hervorgehoben werden sollen
    private Set<Integer> highlightIndices = new HashSet<>();

    // einzige Instanz der Klasse
    private static SortingAnimation instance;

    // Fenster (JFrame) für die Animation
    private JFrame frame;

    /**
     * Panel für die Animation.
     * 
     * Zeichnet die Rechtecke für die einzelnen Elemente des Arrays.
     */
    private class SortingPanel extends javax.swing.JPanel {

        // Überschreiben der paintComponent-Methode für Custom Painting
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            // falls das Array null ist, nichts zeichnen
            if (array == null) {
                return;
            }

            // Berechnung der Breite und Höhe der Rechtecke
            double width = (double) getWidth() / array.length;
            double height = (double) getHeight() / array.length;

            // Jedes Element im Array zeichnen
            for (int i = 0; i < array.length; i++) {
                int value = array[i];

                // Farbe nach Highlight-Index und Wert (gerade/ungerade)
                if (highlightIndices.contains(i)) {
                    g.setColor(Color.YELLOW);
                } else if (value % 2 == 0) {
                    int colorValue = 50 + (int) ((value / (double) array.length) * 150);
                    g.setColor(new Color(colorValue/2, colorValue/2, colorValue));
                } else {
                    int colorValue = 100 + (int) ((value / (double) array.length) * 150);
                    g.setColor(new Color(colorValue/2, colorValue/2, colorValue));
                }

                // Berechnung der Position und Größe des Rechtecks für das aktuelle Element
                double x = i * width;
                double y = getHeight() - (value * height);
                g.fillRect((int) x, (int) y, (int) width - 1, (int) (value * height));
            }
        }
    }

    /**
     * Zeigt das Array in der Animation an, ohne Verzögerung oder Hervorhebung.
     * 
     * @param array Das Array, das angezeigt werden soll
     */
    public void showArray(int[] array) {
        showArray(array, 0);
    }

    /**
     * Zeigt das Array in der Animation an, mit Verzögerung und Hervorhebung.
     * 
     * @param array            Das Array, das angezeigt werden soll
     * @param delayTime        Die Verzögerung in Millisekunden
     * @param highlightIndices Die Indizes, die hervorgehoben werden sollen
     *                         (optional)
     */
    public void showArray(int[] array, int delayTime, int... highlightIndices) {
        this.highlightIndices.clear();
        for (int index : highlightIndices) {
            this.highlightIndices.add(index);
        }

        this.array = array;
        frame.repaint();

        // Verzögerung der Animation
        if (delayTime > 0) {
            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Konstruktor für die Animationsvisualisierung.
     * 
     * Initialisiert und zeigt ein Fenster mit der Animation des Sortieralgorithmus.
     */
    private SortingAnimation() {
        // Initialisierung des Fensters
        frame = new JFrame();
        frame.setTitle("Sorting Animation");
        frame.add(new SortingPanel());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Gibt die einzige Instanz der Klasse zurück (Singleton-Pattern).
     * 
     * @return Die einzige Instanz der Klasse
     */
    public static SortingAnimation instance() {
        if (instance == null) {
            instance = new SortingAnimation();
        }
        return instance;
    }
}
