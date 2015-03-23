package algo.string;

/**
 * Created by bernard on 13/3/15.
 */
public class KnuthMorrisPratt {

    private static int[] perProcessShift(String pattern) {
        char[] p = pattern.toCharArray();
        int[] shift = new int[p.length + 1];
        int i = 0;
        int j = -1;

        shift[0] = -1;

        while(i < p.length)  {
            while(j >= 0 && p[i] != p[j]) j = shift[j];
            i++;
            j++;
            shift[i] = j;
        }

        return shift;
    }

    private static int search(String text, String pattern) {
        int[] shift = perProcessShift(pattern);
        char[] p = pattern.toCharArray();
        char[] t = text.toCharArray();
        int i = 0;
        int j = 0;

        while(i < t.length) {
            while(j >= 0 && t[i] != p[j]) j = shift[j];
            i++;
            j++;
            if(j == p.length) return i - j;
        }

        return -1;
    }

    public static void main(String... args) {
        long before = System.currentTimeMillis();
        System.out.println("The result is " + search("This is a simple example.", "example"));
        long after = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (after - before));
    }
}
