import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length == 1){
            return;
        }
        int length = arr.length;
        int midIndex = length / 2;
        T[] leftArr = Sorting.subArray(arr, 0, midIndex);
        T[] rightArr = Sorting.subArray(arr, midIndex, length);
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        //Loop through left side comparing to right and replacing into array
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length){
            if (comparator.compare(leftArr[i], rightArr[j]) < 0){
                arr[i + j] = leftArr[i];
                i++;
            } else {
                arr[i + j] = rightArr[j];
                j++;
            }
        }
        while (i < leftArr.length){
            arr[i + j] = leftArr[i];
            i++;
        }
        while (j < rightArr.length){
            arr[i + j] = rightArr[j];
            j++;
        }



    }

    private static <T> T[] subArray(T[] arr, int startIndex, int endIndex) {
        T[] subArray = (T[]) new Object[endIndex - startIndex];
        for (int i = startIndex; i < endIndex; i++) {
            subArray[i - startIndex] = arr[i];
        }
        return subArray;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int k = 0;
        int maxSize = 0;
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < arr.length; i++){
            if (Math.abs(arr[i]) > maxSize){
                maxSize = Math.abs(arr[i]);
            }
        }
        k = Integer.toString(maxSize).length();
        for (int i = 0; i < k; i++){
            for (int j = 0; j < arr.length; j++){
                int currentDigit;
                if (i == 0){
                    currentDigit = arr[j] % 10 + 9;
                } else {
                    currentDigit = (int) (arr[j] / (Math.pow(10, i))) + 9;
                }
                if (buckets[currentDigit] == null){
                    LinkedList<Integer> newPlacement = new LinkedList<>();
                    newPlacement.addLast(arr[j]);
                    buckets[currentDigit] = newPlacement;
                } else {
                    buckets[currentDigit].addLast(arr[j]);
                }
            }
            int idx = 0;
            for (LinkedList<Integer> item : buckets){
                if (item == null){
                    continue;
                }
                while (item.size() != 0){
                    arr[idx] = item.remove();
                    idx++;
                }
            }
        }
    }
}