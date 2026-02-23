package ch.hslu.w0BasicsZug;

import java.util.Objects;

public final class Wagen {
    private final String id;
    private int capacity;
    private Wagen nachfolger;

    public Wagen(String id, int capacity) {
        this(id, capacity, null);
    }

    public Wagen(String id, int capacity, Wagen nextWagen) {
        this.id = id;
        this.capacity = capacity;
        this.nachfolger = nextWagen;
    }
//    public static int berechneGesamtPlätze(Wagen wagen){
//        int summe;
//        return wagen.getCapacity() + Wagen.berechneGesamtPlätze(wagen.nachfolger);
//    }

//    public static int berechneGesamtPlätze(Wagen wagen) {
//        // Basisfall: Wenn kein Wagen mehr da ist, sind es 0 Plätze
//        if (wagen == null) {
//            return 0;
//        }
//        // Rekursionsschritt: Eigene Plätze + Plätze aller Nachfolger
//        return wagen.getCapacity() + Wagen.berechneGesamtPlätze(wagen.getNachfolger());
//    }

    // Statische Methode zur Berechnung der Gesamtplätze im Zug
    public static int berechneGesamtPlätze(Wagen wagen) {
        int gesamt = 0;
        Wagen aktuell = wagen;
        while (aktuell != null) {
            gesamt += aktuell.getCapacity();
            aktuell = aktuell.getNachfolger(); // Springe zum nächsten Wagen [cite: 68]
        }
        return gesamt;
    }

    public void setNachfolger(Wagen nextWagen){
        this.nachfolger = nextWagen;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Wagen getNachfolger() {
        return nachfolger;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Wagen wagen)) return false;
        return Objects.equals(id, wagen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
