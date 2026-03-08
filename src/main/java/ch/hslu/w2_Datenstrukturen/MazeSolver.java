package ch.hslu.w2_Datenstrukturen;

import java.util.List;

public class MazeSolver {

    // Definition der Zeichen gemäß Aufgabenstellung
    private static final char WALL = '#';
    private static final char EXIT = 'X';
    private static final char VISITED = '.';
    private static final char EMPTY = ' ';

    // Beispiel-Labyrinth (muss ggf. an deine Ilias-Vorlage angepasst werden)
    private char[][] maze = {
            {' ', '#', ' ', ' ', ' '},
            {' ', '#', ' ', '#', ' '},
            {' ', ' ', ' ', '#', ' '},
            {'#', '#', ' ', '#', ' '},
            {' ', ' ', ' ', ' ', 'X'}
    };

    /**
     * Aufgabe c) Implementierung der Methode printMaze()
     * Gibt das Labyrinth auf der Konsole aus.
     */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Aufgabe d) Rekursive Suche nach Wegen durch das Labyrinth.
     * Implementiert exakt den vorgegebenen Pseudocode.
     */
    public void findPath(int zeile, int spalte) {
        // 1. Überprüfung auf ungültige Zeilen- oder Spaltennummern (Bounds Check)
        if (zeile < 0 || zeile >= maze.length || spalte < 0 || spalte >= maze[0].length) {
            return;
        }

        // 2. Zielprüfung: Wenn das Feld 'X' enthält
        if (maze[zeile][spalte] == EXIT) {
            System.out.println("Ziel gefunden!");
            printMaze(); // Gebe das gesamte Labyrinth aus
            return;
        }

        // 3. Wegsuche: Nur wenn das Feld ein Leerzeichen ist
        if (maze[zeile][spalte] == EMPTY) {
            // Markiere das Feld als besucht
            maze[zeile][spalte] = VISITED;

            // Rufe findPath rekursiv auf allen Nachbarfeldern auf (Nord, Süd, West, Ost)
            findPath(zeile - 1, spalte); // Nord
            findPath(zeile + 1, spalte); // Süd
            findPath(zeile, spalte - 1); // West
            findPath(zeile, spalte + 1); // Ost

            // Backtracking: Setze das Feld wieder auf Leerzeichen zurück
            // Dies ermöglicht es, andere mögliche Wege über dieses Feld zu finden.
            maze[zeile][spalte] = EMPTY;
        }
    }

    /**
     * Aufgabe e) Den Algorithmus so verändern, dass er nach dem ersten Fund abbricht.
     * Dazu müsste die Methode einen boolean zurückgeben.
     */
    public boolean findFirstPathOnly(int zeile, int spalte) {
        if (zeile < 0 || zeile >= maze.length || spalte < 0 || spalte >= maze[0].length) return false;
        if (maze[zeile][spalte] == EXIT) {
            printMaze();
            return true;
        }
        if (maze[zeile][spalte] == EMPTY) {
            maze[zeile][spalte] = VISITED;

            // Wenn einer der Nachbarn den Ausgang findet, breche sofort ab (true zurückgeben)
            if (findFirstPathOnly(zeile - 1, spalte) ||
                    findFirstPathOnly(zeile + 1, spalte) ||
                    findFirstPathOnly(zeile, spalte - 1) ||
                    findFirstPathOnly(zeile, spalte + 1)) {
                return true;
            }
            // Kein Backtracking nötig, wenn man nur den ersten Pfad will und
            // bereits besuchte Felder für diesen Durchlauf gesperrt bleiben sollen.
        }
        return false;
    }

    public static void main(String[] args) {
        MazeSolver solver = new MazeSolver();
        System.out.println("Startzustand:");
        solver.printMaze();

        System.out.println("Suche alle Wege:");
        solver.findPath(0, 0); // Start bei (0,0)
    }
}
