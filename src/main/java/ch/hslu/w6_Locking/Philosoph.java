package ch.hslu.w6_Locking;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Philosoph {
    private Gabel gabelL;
    private Gabel gabelR;
    private int id;

    private static final Logger LOG = LoggerFactory.getLogger(Philosoph.class);


    public Philosoph(int id, Gabel gabelL, Gabel gabelR) {
        this.gabelL = gabelL;
        this.gabelR = gabelR;
        this.id = id;
    }

    public Gabel getGabelL() {
        return gabelL;
    }

    public Gabel getGabelR() {
        return gabelR;
    }
// TODO: probiere Gabel / lege Gabel zurück
    public void probiereZuEssen(){
        if (gabelL.getZustand() == Gabel.Zustand.FREI
                && gabelR.getZustand() == Gabel.Zustand.FREI) {
            LOG.info("beide Gabeln ( {} & {} ) sind frei bei P: {}", gabelL.getNummer(), gabelR.getNummer(), this.id);
            gabelL.setZustand(Gabel.Zustand.BESETZT);
            gabelR.setZustand(Gabel.Zustand.BESETZT);

            this.esse();

        }
    }

    public void esse(){
        int randomTime = ThreadLocalRandom.current().nextInt(200, 400);
        try {
                Thread.sleep(randomTime);
                LOG.info("Philiosph Nr: {} hat gegessen", this.id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void Studiere(){
        int randomTime = ThreadLocalRandom.current().nextInt(200, 400);

         try {
             Thread.sleep(randomTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            new Philosoph(i, new Gabel(i), new Gabel(i%5+1));
//            LOG.info("Philosof {} erstellt | Gabel L = {} | Gabel R = {}", i, );
//        }

        Gabel g1 = new Gabel(1);
        Gabel g2 = new Gabel(2);
        Gabel g3 = new Gabel(3);
        Gabel g4 = new Gabel(4);
        Gabel g5 = new Gabel(5);
        LOG.info("Gabeln etstellt");

        Philosoph p1 = new Philosoph(1,g1, g2);
        Philosoph p2 = new Philosoph(1,g2, g3);
        Philosoph p3 = new Philosoph(1,g3, g4);
        Philosoph p4 = new Philosoph(1,g4, g5);
        Philosoph p5 = new Philosoph(1,g5, g1);
        LOG.info("Philosophen erstellt");

        while (true){
            p1.probiereZuEssen();
            p2.probiereZuEssen();
            p3.probiereZuEssen();
            p4.probiereZuEssen();
            p5.probiereZuEssen();
        }
    }
}
