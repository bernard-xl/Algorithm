package algo.number;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernard on 18/3/15.
 */
public class Permutation {

    private static interface PermutationListener<T> {
        public void onResultGenerated(List<T> list);
    }

    private static <T extends Comparable<T>> void heapPermutation(List<T> list, int n, PermutationListener<T> listener) {
        if(n == 0) listener.onResultGenerated(list);

        for(int i = 0; i <= n; i++) {
            heapPermutation(list, n - 1, listener);
            Collections.swap(list, (n % 2 == 0)? i : 0, n);
        }
    }

    public static void main(String... args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        heapPermutation(list, list.size() - 1, new PermutationListener<Integer>() {
            int i = 0;
            @Override
            public void onResultGenerated(List<Integer> list) {
                System.out.print("" + ++i + ": ");
                for(Integer i : list) System.out.print(" " + i);
                System.out.println();
            }
        });
    }
}
