package ch.hslu.w2_Datenstrukturen;

/**
 * Dieses Interface dient als Vertrag um ein Array Stack zu implementieren
 */
public interface ArrayStackAble {
    /**
     * push ist = neues String Element in Stack einfügen
     * @param item
     */
    void push(String item);

    /**
     * pop returend den wert und löscht ihn aus dem Stack
     * @return
     */
    String pop();

    /**
     * die weiteren 3 Methoden sind selbstverständlich
     * @return
     */
    int getSize();
    boolean isFull();
    boolean isEmpty();
}
