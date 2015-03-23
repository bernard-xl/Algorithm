package algo.string;

/**
 * Created by bernard on 19/3/15.
 */
public class LongestCommonSequence {

    private static String longestCommSequence(String text, String pattern) {
        char[] p = (" " + pattern).toCharArray() ;
        char[] t = (" " + text).toCharArray();
        int[][] map = new int[p.length][t.length];

        for(int m = 1; m < p.length; m++) {
            for(int n = 1; n < t.length; n++) {
                if(p[m] == t[n]) map[m][n] = map[m - 1][n - 1] + 1;
                else map[m][n] = Math.max(map[m][n -1], map[m - 1][n]);
            }
        }

        StringBuilder strBuilder = new StringBuilder(p.length);
        int row = p.length - 1;
        int column = t.length - 1;
        int current = map[row][column];

        while(row >= 1 && column >= 1) {
            if(map[row - 1][column] == current) {
                row--;
            } else if(map[row][column - 1] == current) {
                column--;
            } else {
                strBuilder.insert(0, p[row]);
                current = map[--row][--column];
            }
        }

        return strBuilder.toString();
    }

    public static void main(String... args) {
        String text= "ABCBDAB";
        String pattern = "BDCABA";

        System.out.println("The result is: " + longestCommSequence(text, pattern));
    }
}
