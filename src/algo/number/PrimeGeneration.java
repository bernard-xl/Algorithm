package algo.number;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bernard on 18/3/15.
 */
public class PrimeGeneration {

    private static List<Integer> generatePrimeNumber(int n) {
        List<Integer> result = new ArrayList<Integer>();
        boolean[] notPrime = new boolean[n + 1];

        for(int i = 2; i <= n; i++) {
            if(!notPrime[i]) {
                result.add(i);
                for(int j = i * i; j <=n; j += i) notPrime[j] = true;
            }
        }

        return result;
    }

    public static void main(String... args) {
        List<Integer> primes = generatePrimeNumber(255);
        System.out.print("The primes under 255 are:");
        for(Integer p : primes) System.out.print(" " + p.toString());
    }
}
