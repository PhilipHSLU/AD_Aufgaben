package ch.hslu.w2_Datenstrukturen;

public class ListGeneric<T> {
    private NodeGeneric<T> head;
    private int size = 0;

    public ListGeneric(NodeGeneric<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public NodeGeneric<T> getHead() {
        return head;
    }

    public void addFirst(T wert) {
        NodeGeneric<T> node = new NodeGeneric<T>(wert);
        node.setNext(head);
        head = node;
        size++;
    }

    public boolean hasValue(T suchWert){
        NodeGeneric<T> current = head;
        while (current != null){
            if (current.getWert().equals(suchWert)){
                return true;
            }
            current = current.getNext();

        }
        return false;
    }

    /**
     * FIFO Prinzip = Queue
     * @return
     */
    public T removeLast() {
        // Fall 0: Liste ist leer
        if (head == null) {
            return null;
        }

        size--; // Wir entfernen sicher etwas, also Grösse verringern

        // Fall 1: Liste hat nur genau ein Element
        // (head.getNext() ist null, also kommt nach dem Kopf nichts mehr)
        if (head.getNext() == null) {
            T wert = head.getWert();
            head = null; // Liste ist nun leer
            return wert;
        }

        // Fall 2: Liste hat mehrere Elemente
        // Wir müssen den VORLETZTEN Knoten finden, um den letzten abzuschneiden.
        NodeGeneric<T> current = head;

        // Wir prüfen: Gibt es einen Übernächsten?
        // Solange "der Nächste vom Nächsten" nicht null ist, sind wir noch nicht beim Vorletzten.
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }

        // Jetzt ist 'current' der vorletzte Knoten.
        // 'current.getNext()' ist der letzte Knoten (den wir löschen wollen).

        T wert = current.getNext().getWert(); // Wert sichern
        current.setNext(null); // Verknüpfung zum letzten Knoten kappen
        return wert;
    }

    public void removeValue(T value){
//       Fall wert gibts nicht
        if (!(hasValue(value))){throw new RuntimeException("wert gibts nicht");}

//        Fall Liste leer
        if (this.head == null){return;}

//        Fall Head löschen
        if (this.head.getWert().equals(value)){
            head = head.getNext();
            size--;
            return;
        }

        // Fall Letztes Element / Mitlleres löschen
        NodeGeneric<T> current = this.head;
        while (current.getNext() == null){
            if (current.getNext().getWert().equals(value)){
                current.setNext(current.getNext().getNext());
                this.size--;
                return;
            }
            current = current.getNext();
        }

    }

}
