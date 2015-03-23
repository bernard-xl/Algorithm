package algo.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Created by bernard on 22/3/15.
 */
public class WordsReverse {
    private static String reverse(String text) {
        Deque<String> stack = new ArrayDeque<String>();
        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        StringBuilder strBuilder = new StringBuilder();
        String delim = "";

        while(tokenizer.hasMoreTokens()) {
            stack.push(tokenizer.nextToken());
        }

        while(!stack.isEmpty()) {
            strBuilder.append(delim).append(stack.pop());
            delim = " ";
        }

        return strBuilder.toString();
    }

    public static void main(String... args) {
        String text = "A quick fox jumped over a lazy dog";
        System.out.println(reverse(text));
    }
}
