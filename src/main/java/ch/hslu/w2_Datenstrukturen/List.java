package ch.hslu.w2_Datenstrukturen;

public class List {
    private Node head;
    private int size = 0;

    public List(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void addFirst(int wert) {
        Node node = new Node(wert);
        node.setNext(head);
        head = node;
        size++;
    }

    public boolean hasValue(int suchWert){
        Node current = head;
        while (current != null){
            if (current.getWert() == suchWert){
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
    public Integer removeLast() {
        // Fall 0: Liste ist leer
        if (head == null) {
            return null;
        }

        size--; // Wir entfernen sicher etwas, also Grösse verringern

        // Fall 1: Liste hat nur genau ein Element
        // (head.getNext() ist null, also kommt nach dem Kopf nichts mehr)
        if (head.getNext() == null) {
            int wert = head.getWert();
            head = null; // Liste ist nun leer
            return wert;
        }

        // Fall 2: Liste hat mehrere Elemente
        // Wir müssen den VORLETZTEN Knoten finden, um den letzten abzuschneiden.
        Node current = head;

        // Wir prüfen: Gibt es einen Übernächsten?
        // Solange "der Nächste vom Nächsten" nicht null ist, sind wir noch nicht beim Vorletzten.
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }

        // Jetzt ist 'current' der vorletzte Knoten.
        // 'current.getNext()' ist der letzte Knoten (den wir löschen wollen).

        int wert = current.getNext().getWert(); // Wert sichern
        current.setNext(null); // Verknüpfung zum letzten Knoten kappen
        return wert;
    }

    public void removeValue(int value){
//       Fall wert gibts nicht
        if (!(hasValue(value))){throw new RuntimeException("wert gibts nicht");}

//        Fall Liste leer
        if (this.head == null){return;}

//        Fall Head löschen
        if (this.head.getWert() == value){
            head = head.getNext();
            size--;
            return;
        }

        // Fall Letztes Element / Mitlleres löschen
        Node current = this.head;
        while (current.getNext() != null){
            if (current.getNext().getWert() == value){
                current.setNext(current.getNext().getNext());
                this.size--;
                return;
            }
            current = current.getNext();
        }

    }

}
