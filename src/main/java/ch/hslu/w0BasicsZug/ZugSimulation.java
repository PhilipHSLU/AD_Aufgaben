package ch.hslu.w0BasicsZug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.hslu.w0BasicsZug.Wagen.berechneGesamtPlätze;


public final class ZugSimulation {
    private static final Logger LOG = LoggerFactory.getLogger(ZugSimulation.class);

    public static void main(final String[] args) {

        // Personenwagen erzeugen,
        // mit unterschiedlichen Kapazitäten
        Wagen wagen1 = new Wagen("W001", 60);
        Wagen wagen2 = new Wagen("W002", 40);
        Wagen wagen3 = new Wagen("W003", 80);
        // Verkettung der Wagen
        wagen1.setNachfolger(wagen2);
        wagen2.setNachfolger(wagen3);
        // Berechnung der Gesamtanzahl Plätze
        int gesamt = berechneGesamtPlätze(wagen1);
        LOG.info("Gesamtanzahl Plätze: " + gesamt);
    }
}
