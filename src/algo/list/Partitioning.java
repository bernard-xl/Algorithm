package algo.list;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by bernard on 21/3/15.
 */
public class Partitioning {

    private static interface Partitioner<T> {
        public boolean test(T element);
    }

    private static <T> void partition(T[] array, Partitioner<T> partitioner) {
        int i = 0;
        int j = array.length - 1;

        while(i < j) {
            while(i < array.length && partitioner.test(array[i])) i++;
            while(j >= 0 && !partitioner.test(array[j])) j--;
            if(i < j) {
                T tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
    }

    public static void main(String... args) {
        Integer[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        partition(array, new Partitioner<Integer>() {
            @Override
            public boolean test(Integer element) {
                return element % 2 != 0;
            }
        });
        System.out.println(Arrays.toString(array));
    }
}
