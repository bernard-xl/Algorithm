package algo.number;

/**
 * Created by bernard on 17/3/15.
 */
public class GreatestCommonDivisor {

    private static int gcd(int a, int b) {
        if(b != 0) return gcd(b, a % b);
        else return a;
    }

    public static void main(String... args) {
        System.out.println("GCD of 3 and 9 is " + gcd(3, 9));
        System.out.println("GCD of 2 and 11 is " + gcd(2, 11));
        System.out.println("GCD of 4 and 16 is " + gcd(4, 16));
    }
}
