package ch.hslu.w2_Datenstrukturen;

public class NodeGeneric<T> {
    private T wert;
    NodeGeneric next;

    public NodeGeneric(T wert) {
        this.wert = wert;
        this.next = null;
    }

    public T getWert() {
        return wert;
    }

    public NodeGeneric<T> getNext() {
        return next;
    }

    public void setNext(NodeGeneric next) {
        this.next = next;
    }
}
