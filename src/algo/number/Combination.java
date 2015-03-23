package algo.number;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by bernard on 22/3/15.
 */
public class Combination {

    private static interface CombinationListener<T> {
        public void onCombinationDiscovered(List<T> comb);
    }

    private static <T> void generateCombination(List<T> list, CombinationListener<T> listener) {
        boolean[] fields = new boolean[list.size()];
        generateCombination(list, fields, 0, listener);
    }

    private static <T> void generateCombination(List<T> list, boolean[] fields, int n, CombinationListener<T> listener) {
        if(n == list.size()) {
            List<T> comb = IntStream.range(0, list.size())
                    .filter(i -> fields[i])
                    .mapToObj(list::get)
                    .collect(Collectors.toList());

            listener.onCombinationDiscovered(comb);
            return;
        }

        fields[n] = false;
        generateCombination(list, fields, n + 1, listener);

        fields[n] = true;
        generateCombination(list, fields, n + 1, listener);
    }

    public static void main(String... args) {
        List<Character> list = Arrays.asList('a', 'b', 'c', 'd');
        generateCombination(list, new CombinationListener<Character>() {
            @Override
            public void onCombinationDiscovered(List<Character> comb) {
                System.out.println(comb.toString());
            }
        });
    }
}
