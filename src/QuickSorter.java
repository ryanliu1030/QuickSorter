import java.util.*;
import java.time.*;
public class QuickSorter {
    //make constructor not accessible
    private QuickSorter() {}

    //assign enums that's needed
    public enum PivotStrategy{
        FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
    }

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException{
        if (list.isEmpty())
            throw new NullPointerException("Argument Invalid.");
        long startTime = System.nanoTime();
        switch (pivotStrategy) {
            case FIRST_ELEMENT ->
                firstElementHelper(list, 0, list.size() - 1);

            case RANDOM_ELEMENT -> randomElementHelper(list, 0, list.size() -1);
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS -> threeMedianRandomHelper(list, 0, list.size() -1);
            case MEDIAN_OF_THREE_ELEMENTS -> threeMedianHelper(list, 0, list.size() - 1);
        }

        long endTime = System.nanoTime();
        return Duration.ofNanos(endTime - startTime);
    }


    public static ArrayList<Integer> generateRandomList(int size){
        ArrayList<Integer> list = new ArrayList<>(size);
        Random rand = new Random();
        for (int i = 0; i < size; i ++){
            int temp = rand.nextInt();
            list.add(temp);
        }
        return list;
    }
    private static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j){
        E temp = list.get(i);
        E temp2 = list.get(j);
        list.set(i, temp2);
        list.set(j, temp);
    }

    private static  <E extends Comparable<E>> int quickSort(ArrayList<E> list, int low, int high){
        E pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++){
            if (list.get(j).compareTo(pivot) <= 0){
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i+1, high);
        return i + 1;
    }
    private static <E extends Comparable<E>> void firstElementHelper(ArrayList<E> list, int low, int high){
        if (low >= high)
            return;

        swap(list, low, high);
        int pivot = quickSort(list, low, high);
        firstElementHelper(list, low, pivot - 1);
        firstElementHelper(list, pivot + 1, high);
    }


    private static <E extends Comparable<E>> void randomElementHelper(ArrayList<E> list, int low, int high){
        if (low >= high)
            return;
        int randPivot = (int)((Math.random() * (high - low)) + low);
        swap(list, randPivot, high);
        int pivot = quickSort(list, low, high);
        randomElementHelper(list, low, pivot - 1);
        randomElementHelper(list, pivot + 1, high);
    }


    private static <E extends Comparable<E>> int median(ArrayList<E> list, int a, int b, int c){
        if (list.get(a).compareTo(list.get(b)) > 0) {
            if (list.get(b).compareTo(list.get(c)) > 0)
                return b;
            else if (list.get(a).compareTo(list.get(c)) > 0)
                return c;
            else
                return a;
        }
        else {
            if (list.get(a).compareTo(list.get(c)) > 0)
                return a;
            else if (list.get(b).compareTo(list.get(c)) > 0)
                return c;
            else return b;
        }

    }
    private static <E extends Comparable<E>> void threeMedianHelper(ArrayList<E> list, int low, int high){
        if (low >= high)
            return;

        int medianPivot = median(list, low, high, (low+high) / 2);
        if(medianPivot != high)
            swap(list, medianPivot, high);

        int pivot = quickSort(list, low, high);
        threeMedianHelper(list, low, pivot - 1);
        threeMedianHelper(list, pivot + 1, high);

    }

    private static <E extends Comparable<E>> void threeMedianRandomHelper(ArrayList<E> list, int low, int high){
        if (low >= high)
            return;

        int randA = (int)((Math.random() * (high - low)) + low);
        int randB = (int)((Math.random() * (high - low)) + low);
        int randC = (int)((Math.random() * (high - low)) + low);

        int medianPivot = median(list, randA, randB, randC);
        if (medianPivot != high)
            swap(list, medianPivot, high);

        int pivot = quickSort(list, low, high);
        threeMedianRandomHelper(list, low, pivot - 1);
        threeMedianRandomHelper(list, pivot + 1, high);
    }

}
