package algo.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by bernard on 22/3/15.
 */
public class BalanceParenthesis {

    private static boolean isBalanced(String text) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : text.toCharArray()) {
            if(c == '(') stack.push(c);
            if(c == ')') {
                if(stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String... args) {
        String text = "((()))";
        System.out.println(isBalanced(text));
    }
}
