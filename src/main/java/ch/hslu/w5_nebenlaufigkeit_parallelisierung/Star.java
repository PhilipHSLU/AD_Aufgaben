package ch.hslu.w5_nebenlaufigkeit_parallelisierung;
// >> java -cp target\classes ch.hslu.w5_nebenlaufigkeit_parallelisierung.Star
   //Bildschirm löschen:
    //      System.out.println("\033[2J");
//    Cursor verstecken:
//          System.out.println("\033[?25l");
//    Stern an Stelle row, column setzen:
//            System.out.println("\033[" + row + ";" + column +" H*");
//    Stern an Stelle row, column löschen:
//            System.out.println("\033[" + row + ";" + column +" H ");

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Star implements Runnable{

    private int delay = ThreadLocalRandom.current().nextInt(50, 101);
    private int column = 0;
    private int row;




    public Star(int row) {
        this.row = row;
    }

    public static String loescheBildS() {
       return  "\033[2J";
    }
    public static String hideCursor() {
        return "\033[?25l";
    }
    public String setStarXY() {
        return "\033[" + row + ";" + column + " H*";
    }
    public String deleteStarXY(){
        return "\033[" + row + ";" + column +" H ";}

    public void move() {
        System.out.print(deleteStarXY());
        try {
            while (true) {
                if (column <= 80) {
                    this.column += 1;
                } else this.column = 1;

                System.out.print(setStarXY());
                Thread.sleep(delay);
                System.out.flush();
            }
        }catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

//    public void move() {
//        while (true) {
//            final int oldColumn = this.column;
//
//            this.column++;
//
//            if(column > 80) {
//                column = 1;
//            }
//
//            // Stern an der NEUEN Position setzen
//            System.out.print("\033[" + this.row + ";" + this.column + "H*");
//
//            // Stern an der ALTEN Position löschen
//            System.out.print("\033[" + this.row + ";" + oldColumn + "H ");
//
//            try {
//                Thread.sleep(this.delay);
//            } catch (InterruptedException e) {
//                return;
//            }
//        }
//    }

    @Override
    public void run() {
        this.move();
    }

    public static void main(String[] args) {
        System.out.print(loescheBildS());
        System.out.print(hideCursor());

        for (int i = 1; i <= 20; i++) {
            Star star = new Star(i);
            Thread t = new Thread(star);
            t.start();
        }
    }
}
