package ch.hslu.w2_Datenstrukturen;

public class Node {
    private int wert;
    Node next;

    public Node(int wert) {
        this.wert = wert;
        this.next = null;
    }

    public int getWert() {
        return wert;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
