package ch.hslu.w3_Tree;


import java.util.ArrayList;
import java.util.List;
// --- AUFGABE 6.b: Grundstruktur ---
// Gemeinsames Interface für alle Knoten (Zahlen und Operatoren)
interface Nodeable {
    String toString();          // Aufgabe 6.c
    int eval();                 // Aufgabe 6.d
    List<String> compile();     // Aufgabe 6.e
}
