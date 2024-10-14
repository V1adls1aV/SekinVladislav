package hw04.sorts;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.SortAlgorithm;
import hw04.sorts.algorithms.SortType;
import java.util.ArrayList;
import java.util.List;

/**
 * Sorter – is the manager for sorting algorithms. Designed with responsibility chain pattern.
 *
 * <p>It is trying to apply each algorithm for sorting till one of algorithms returns result.
 * Sorting algorithms will be lauched in order they have been added to the list of Sorter.
 */
public class Sorter {
  public List<SortAlgorithm> sortAlgorithms = new ArrayList<>();

  /**
   * Pass the list of algorithms to be applied in the order you want.
   *
   * @param algorithms the list of algorithms to be applied
   */
  public Sorter(List<SortAlgorithm> algorithms) {
    this.sortAlgorithms = algorithms;
  }

  /**
   * Applies algorithms one by one (of specified type if needed.
   *
   * @param list the list to be sorted
   * @return sorted list
   * @throws ListSizeException if all algorithms have failed.
   */
  public ArrayList<Integer> sort(List<Integer> list) throws ListSizeException {
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

  /**
   * Applies algorithms one by one (of specified type if needed.
   *
   * @param list the list to be sorted
   * @param algorithm the algorithm type to be applied
   * @return sorted list
   * @throws ListSizeException if all algorithms have failed.
   */
  public ArrayList<Integer> sort(List<Integer> list, SortType type) throws ListSizeException {

    for (SortAlgorithm algorithm : this.sortAlgorithms) {
      if (type.equals(algorithm.getSortType())) {
        try {
          return algorithm.sort(list);
        } catch (ListSizeException e) {
        }
      }
    }

    throw new ListSizeException(
        "No suitable sorting algorithm found. Size of passed list "
            + list.size()
            + " is bigger than size each algorithm of specified type "
            + type
            + " can handle.");
  }
}
