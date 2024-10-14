package hw04.sorts.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Merge sort algorithm implementation. You may specify max list size to be sorted. */
public class MergeSort extends SortAlgorithm {
  public MergeSort() {}

  public MergeSort(int maxListSize) {
    this.MAX_LIST_SIZE = maxListSize;
  }

  @Override
  public ArrayList<Integer> sort(List<Integer> list) {
    this.checkListSize(list.size());

    ArrayList<Integer> result = new ArrayList<>(list);
    Collections.sort(result);
    return result;
  }
}
