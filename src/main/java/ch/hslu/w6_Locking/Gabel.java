package ch.hslu.w6_Locking;

public class Gabel {
    private final int Nummer;
    public enum Zustand{BESETZT, FREI}
    private Zustand zustand = Zustand.FREI;

    public Gabel(int nummer) {
        Nummer = nummer;
    }

    public int getNummer() {
        return Nummer;
    }

    public Zustand getZustand() {
        return zustand;
    }

    public void setZustand(Zustand zustand) {
        // TODO: wenn eh get /set dann kann man auch public Attr machen oder?
        this.zustand = zustand;
    }
}
