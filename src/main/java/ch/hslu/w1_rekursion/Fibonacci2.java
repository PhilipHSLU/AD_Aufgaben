package ch.hslu.w1_rekursion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fibonacci2 {
    private static final Logger LOG = LoggerFactory.getLogger(Fibonacci2.class);

    public static int fiborec1(int n){
        if (n <= 1){return n;}

        return fiborec1(n-1) + fiborec1(n-2);
    }

    public static void main(String[] args) {
        LOG.info("fib {}", fiborec1(6));
    }
}
