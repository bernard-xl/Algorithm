package algo.string;

import java.util.*;

/**
 * Created by bernard on 13/3/15.
 */
public class BoyerMoore {

    private static class BadCharMap extends HashMap<Character, Integer> {

        @Override
        public Integer get(Object c) {
            Integer r = super.get(c);
            return (r == null)? -1 : r;
        }
    }

    private static BadCharMap preProcessBadChar(String pattern) {
        BadCharMap badCharMap = new BadCharMap();
        char[] p = pattern.toCharArray();

        for(int i = 0; i < p.length; i++) {
            badCharMap.put(p[i], i);
        }

        return badCharMap;
    }

    private static int[] preProcessGoodSuffix(String pattern) {
        int m = pattern.length();
        int[] f = new int[m + 1];
        int[] s = new int[m + 1];
        char[] p = pattern.toCharArray();
        int i = m;
        int j = m + 1;

        f[i] = j;

        while(i > 0) {
            while(j <= m && p[i - 1] != p[j - 1]) {
                if(s[j] == 0) s[j] = j - i;
                j = f[j];
            }
            f[--i] = --j;
        }

        j = f[0];
        for(i = 0; i < s.length; i++) {
            if(s[i] == 0) s[i] = j;
            if(j == i) j = f[j];
        }

        return s;
    }

    private static int search(String text, String pattern) {
        BadCharMap bc = preProcessBadChar(pattern);
        int[] gs = preProcessGoodSuffix(pattern);
        char[] t = text.toCharArray();
        char[] p = pattern.toCharArray();
        int i = 0;

        while(i <= t.length - p.length) {
            int j = p.length - 1;
            while(j >= 0 && t[i + j] == p[j]) j--;
            if(j < 0) return i;
            else i += Math.max(j - bc.get(t[i + j]), gs[j + 1]);
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
