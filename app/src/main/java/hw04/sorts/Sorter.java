package hw04.sorts;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.SortAlgorithm;
import java.util.ArrayList;
import java.util.List;

public class Sorter {
  public List<SortAlgorithm> sortAlgorithms = new ArrayList<>();

  public Sorter(List<SortAlgorithm> algorithms) {
    this.sortAlgorithms = algorithms;
  }

  public ArrayList<Integer> sort(List<Integer> list) {
    for (SortAlgorithm algorithm : this.sortAlgorithms) {
      try {
        return algorithm.sort(list);
      } catch (ListSizeException e) {
      }
    }

    throw new ListSizeException(
        "No suitable sorting algorithm found. Size of passed list "
            + list.size()
            + " is bigger than size each algorithm can handle.");
  }
}
