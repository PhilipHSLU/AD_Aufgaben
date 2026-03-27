package ch.hslu.w2_Datenstrukturen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    private char[][] maze;

    // Zählt wie viele Wege zum Ziel gefunden wurden
    private int anzahlWege = 0;

    public MazeSolver(char[][] maze) {
        this.maze = maze;
    }

    // c) Gibt das Labyrinth auf der Konsole aus
    private void printMaze() {
        for (int zeile = 0; zeile < maze.length; zeile++) {
            for (int spalte = 0; spalte < maze[zeile].length; spalte++) {
                System.out.print(maze[zeile][spalte]);
            }
            System.out.println(); // neue Zeile nach jeder Reihe
        }
        System.out.println(); // Leerzeile zur Trennung
    }

    // d) Rekursive Methode, die alle Wege durch das Labyrinth sucht
    public void findPath(int zeile, int spalte) {

        // Schritt 1: Ungültige Position? (ausserhalb des Labyrinths) -> abbrechen
        if (zeile < 0 || zeile >= maze.length || spalte < 0 || spalte >= maze[0].length) {
            return;
        }

        // Schritt 2: Ziel 'X' erreicht? -> Labyrinth ausgeben und aufhören
        if (maze[zeile][spalte] == 'X') {
            anzahlWege++;
            System.out.println("=== Weg " + anzahlWege + " gefunden! ===");
            printMaze();
            return;
        }

        // Schritt 3: Nur auf freien Feldern (Leerzeichen) weitergehen
        if (maze[zeile][spalte] == ' ') {

            // Dieses Feld als "besucht" markieren
            maze[zeile][spalte] = '.';

            // Rekursiv alle 4 Richtungen ausprobieren (Nord, Süd, West, Ost)
            findPath(zeile - 1, spalte); // Nord
            findPath(zeile + 1, spalte); // Süd
            findPath(zeile, spalte - 1); // West
            findPath(zeile, spalte + 1); // Ost

            // Feld wieder freigeben (damit andere Wege es benutzen können)
            maze[zeile][spalte] = ' ';
        }

        // Wenn das Feld '#' oder '.' ist, machen wir nichts (Wand oder schon besucht)
    }

    // f) Liest largeMaze.txt und gibt es als char[][] zurück
    public static char[][] loadMazeFromFile(final String dateipfad) throws IOException {
        final List<String> zeilen = new ArrayList<>();

        // Datei Zeile für Zeile einlesen
        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                zeilen.add(zeile);
            }
        }

        // Längste Zeile bestimmen (damit alle Zeilen gleich lang sind)
        int maxLaenge = 0;
        for (final String zeile : zeilen) {
            if (zeile.length() > maxLaenge) {
                maxLaenge = zeile.length();
            }
        }

        // String-Liste in char[][] umwandeln
        final char[][] labyrinth = new char[zeilen.size()][maxLaenge];
        for (int i = 0; i < zeilen.size(); i++) {
            final String zeile = zeilen.get(i);
            for (int j = 0; j < maxLaenge; j++) {
                // Falls die Zeile kürzer ist, mit Leerzeichen auffüllen
                labyrinth[i][j] = (j < zeile.length()) ? zeile.charAt(j) : ' ';
            }
        }

        return labyrinth;
    }

    public static void main(final String[] args) throws IOException {
        char[][] mazeExample = {
                { ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#' },
                { ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#' },
                { ' ', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
                { ' ', '#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#' },
                { '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#' },
                { '#', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ' },
                { ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
                { ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#' },
                { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' } };

        MazeSolver solver = new MazeSolver(mazeExample);

        // Suche starten vom Startfeld (0, 0)
        solver.findPath(0, 0);
        System.out.println("Gesamtanzahl Wege: " + solver.anzahlWege);

        // f) Grosses Labyrinth aus Datei laden und einen Weg finden
        System.out.println("\n=== Grosses Labyrinth (largeMaze.txt) ===");
        final char[][] grossesLabyrinth = loadMazeFromFile(
                "src/main/java/ch/hslu/w2_Datenstrukturen/largeMaze.txt");
        final MazeSolver grosserSolver = new MazeSolver(grossesLabyrinth);
        grosserSolver.findPath(0, 0);
        System.out.println("Gesamtanzahl Wege (grosses Labyrinth): " + grosserSolver.anzahlWege);
    }
}
