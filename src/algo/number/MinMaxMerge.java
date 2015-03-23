package algo.number;

import java.util.Arrays;

/**
 * Created by bernard on 21/3/15.
 */
public class MinMaxMerge {

    private static class MinMax {
        public int min;
        public int max;

        public static MinMax of(int min, int max) {
            MinMax r = new MinMax();
            r.min = min;
            r.max = max;
            return r;
        }

        public MinMax merge(MinMax that) {
            MinMax r = new MinMax();
            r.min = Math.min(this.min, that.min);
            r.max = Math.max(this.max, that.max);
            return r;
        }
    }

    private static MinMax findMinMax(int[] numbers) {
        if(numbers.length == 1)
            return MinMax.of(numbers[0], numbers[0]);

        MinMax leftResult = findMinMax(Arrays.copyOfRange(numbers, 0, numbers.length / 2));
        MinMax rightResult = findMinMax(Arrays.copyOfRange(numbers, numbers.length/2, numbers.length));

        return leftResult.merge(rightResult);
    }

    public static void main(String... args) {
        int[] numbers = { 9 };
        MinMax minMax = findMinMax(numbers);
        System.out.printf("The minimum is %d, maximum is %d\n", minMax.min, minMax.max);
    }
}
