package ch.hslu.w3_Tree;

// --- KNOTENKLASSE (Node) ---
// Repräsentiert ein einzelnes Element im Baum.
class Node {
    int data;       // Das Datenelement (hier einfachheitshalber ein int)
    Node left;      // Referenz auf das linke Kind (kleinere Werte)
    Node right;     // Referenz auf das rechte Kind (grössere Werte)
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    // Hilfreich für das Logging (Aufgabe 5.d)
    @Override
    public String toString() {
        return "Node[" + data + "]";
    }
}

