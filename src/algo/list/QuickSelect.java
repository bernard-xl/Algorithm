package algo.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bernard on 21/3/15.
 */
public class QuickSelect {

    private static <T extends Comparable<T>> int partition(List<T> list, int pivotIdx) {
        int i = 0;
        int j = list.size() - 1;
        T pivot = list.get(pivotIdx);

        Collections.swap(list, 0, pivotIdx);

        while(i < j) {
            while(i < list.size() && list.get(i).compareTo(pivot) <= 0) i++;
            while(j >= 0 && list.get(j).compareTo(pivot) > 0) j--;

            if(i < j) Collections.swap(list, i, j);
        }

        Collections.swap(list, j, 0);
        return j;
    }

    private static <T extends Comparable<T>> T selectSmallest(List<T> list, int n) {
        int pivotIdx = partition(list, 0);

        if(n < pivotIdx)
            return selectSmallest(list.subList(0, pivotIdx), n);
        else if(n > pivotIdx)
            return selectSmallest(list.subList(pivotIdx + 1, list.size()), n - pivotIdx - 1);
        else
            return list.get(n);
    }

    public static void main(String... args) {
        List<Integer> list = Arrays.asList(4, 5, 6, 7, 8, 9, 11, 1, 2, 3);
        int smallest = 9;
        System.out.printf("The %d smallest element is %d\n", smallest, selectSmallest(list, smallest));
    }
}
