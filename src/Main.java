import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] ages = new Integer[]{5, 7, 8, 9, 3, 6, 4, 6, 25};
        Comparator<Integer> compare = (left, right) -> (int)(left-right);
        Sorting.mergeSort(ages, compare);
//        int[] ages = {-34, 22, 56, 2, 17, 11, 55, 4, -5, -9, -33};
//        Sorting.lsdRadixSort(ages);
//        System.out.println("hey");
        System.out.println(Arrays.toString(ages));

    }
}
