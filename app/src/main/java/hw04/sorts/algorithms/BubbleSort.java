package hw04.sorts.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort extends SortAlgorithm {
  public BubbleSort() {}

  public BubbleSort(int maxListSize) {
    this.MAX_LIST_SIZE = maxListSize;
  }

  @Override
  public ArrayList<Integer> sort(List<Integer> list) {
    this.checkListSize(list.size());

    ArrayList<Integer> result = new ArrayList<>(list);

    for (int i = 0; i < result.size(); i++) {
      for (int j = i + 1; j < result.size(); j++) {
        if (result.get(i) > result.get(j)) {
          Integer temp = result.get(j);
          result.set(j, result.get(i));
          result.set(i, temp);
        }
      }
    }
    return result;
  }
}
