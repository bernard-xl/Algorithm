package algo.number;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by bernard on 21/3/15.
 */
public class PrimalityTest {

    private static final int TRIALS = 128;

    private static boolean test(int n) {
        Random rnd = new Random();
        for(int i = 0; i < TRIALS; i++) {
            int a = rnd.nextInt(n - 1) + 1;
            BigInteger test = BigInteger.valueOf(a).pow(n - 1).mod(BigInteger.valueOf(n));
            if(!test.equals(BigInteger.ONE)) return false;
        }
        return true;
    }

    public static void main(String... args) {
         int subject = 71;
         System.out.printf("Is %d a prime number? %s\n", subject, test(subject)? "Yes" : "No");
    }
}
