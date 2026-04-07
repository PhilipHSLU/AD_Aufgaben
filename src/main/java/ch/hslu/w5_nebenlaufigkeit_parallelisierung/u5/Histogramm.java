package ch.hslu.w5_nebenlaufigkeit_parallelisierung.u5;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;

public class Histogramm {

    public static String path = "src/main/java/ch/hslu/w5_nebenlaufigkeit_parallelisierung/u5/bridge.jpg";
    public static byte[] getPixels()throws IOException{
    BufferedImage source = ImageIO.read(new FileInputStream(path));
    byte[] pixels = ((DataBufferByte) source.getRaster().getDataBuffer()).getData();
    return pixels;
}
    public Histogramm()  {
    }

    private static void fillHistogram(int[] histogram, byte[] pixels, int from, int to) {
        for (int i = from; i < to; ++i) {
            ++histogram[0xff & pixels[i]]; // convert byte to int
        }
    }
    public static int[] getHistogramSequential(byte[] pixels) {
        int[] histogram = new int[256];
        fillHistogram(histogram, pixels, 0, pixels.length);
        return histogram;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getHistogramSequential(getPixels()));
    }
}
