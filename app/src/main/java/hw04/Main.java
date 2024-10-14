package hw04;

import hw04.sorts.Sorter;
import hw04.sorts.algorithms.BubbleSort;
import hw04.sorts.algorithms.MergeSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> result = generateSorted();
    System.out.println("Sorted list: " + result);
  }

  public static ArrayList<Integer> generateSorted() {
    List<Integer> list = new ArrayList<Integer>();
    Random random = new Random();
    for (int i = 0; i < 16; i++) {
      list.add(random.nextInt(100));
    }

    Sorter sorter = new Sorter(Arrays.asList(new BubbleSort(10), new MergeSort()));
    return sorter.sort(list);
  }
}
