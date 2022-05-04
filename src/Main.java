import java.time.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 4) {
            File reportF = new File(args[1]);
            File unsortF = new File(args[2]);
            File sortF = new File(args[3]);

            PrintWriter report = new PrintWriter(reportF);
            PrintWriter unsorted = new PrintWriter(unsortF);
            PrintWriter sorted = new PrintWriter(sortF);

            int size = Integer.parseInt(args[0]);

            ArrayList<Integer> list = QuickSorter.generateRandomList(size);
            unsorted.println(list);
            unsorted.close();
            ArrayList<Integer> first = new ArrayList<>(list);
            ArrayList<Integer> random = new ArrayList<>(list);
            ArrayList<Integer> median3 = new ArrayList<>(list);
            ArrayList<Integer> median3Rand = new ArrayList<>(list);

            Duration firstElement, randElement, medianThree, medianRandThree;
            firstElement = QuickSorter.timedQuickSort(first, QuickSorter.PivotStrategy.FIRST_ELEMENT);
            randElement = QuickSorter.timedQuickSort(random, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
            medianRandThree = QuickSorter.timedQuickSort(median3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
            medianThree = QuickSorter.timedQuickSort(median3Rand, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
            sorted.println(first);
            sorted.close();

            report.println("Array Size = " + size);
            report.println("FIRST_ELEMENT : " + firstElement);
            report.println("RANDOM_ELEMENT : " + randElement);
            report.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + medianRandThree);
            report.println("MEDIAN_OF_THREE_ELEMENTS : " + medianThree);
            report.close();
        }
        else
            System.out.println("Please input 4 lines for the commands.");
    }
}