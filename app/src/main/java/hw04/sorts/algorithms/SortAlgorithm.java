package hw04.sorts.algorithms;

import hw04.excepitons.ListSizeException;
import java.util.ArrayList;
import java.util.List;

public abstract class SortAlgorithm {
  protected Integer MAX_LIST_SIZE = null;

  public void checkListSize(int size) {
    if (MAX_LIST_SIZE != null && size > MAX_LIST_SIZE) {
      throw new ListSizeException(size);
    }
  }

  public abstract ArrayList<Integer> sort(List<Integer> list) throws ListSizeException;
}
