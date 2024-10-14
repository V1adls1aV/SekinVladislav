package hw04.sorts.algorithms;

import hw04.excepitons.ListSizeException;
import java.util.ArrayList;
import java.util.List;

/** Abstract class for sorting algorithms. You may specify max list size to be sorted. */
public abstract class SortAlgorithm {
  protected Integer MAX_LIST_SIZE = null;

  /**
   * Throws an exception if size of the list is not suitable for this algorithm.
   *
   * @param size the list size
   * @throws ListSizeException
   */
  public void checkListSize(int size) throws ListSizeException {
    if (MAX_LIST_SIZE != null && size > MAX_LIST_SIZE) {
      throw new ListSizeException(size);
    }
  }

  /**
   * Sorts the list.
   *
   * @param list
   * @return sorted copy of passed list
   * @throws ListSizeException if size is not suitable for this algorithm
   */
  public abstract ArrayList<Integer> sort(List<Integer> list) throws ListSizeException;
}
