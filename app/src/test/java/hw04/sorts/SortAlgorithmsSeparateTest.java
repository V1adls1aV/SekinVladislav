package hw04.sorts;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Separate tests for each of the sort algorithms from hw04.sorts.algorithms. */
public class SortAlgorithmsSeparateTest {
  @Test
  public void testSorting() {
    ArrayList<Integer> list = new ArrayList<>(List.of(8, 3, 2, 6, 4, 1, 5, 7));
    ArrayList<Integer> correct = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));

    for (SortAlgorithm algorithm : List.of(new MergeSort(), new BubbleSort())) {
      ArrayList<Integer> result = algorithm.sort(list);
      assertTrue(
          result.equals(correct),
          "The " + algorithm.getClass().getSimpleName() + " algorithm has crashed.");
    }
  }

  @Test
  public void testFailingSorting() {
    for (SortAlgorithm algorithm : List.of(new MergeSort(5), new BubbleSort(5))) {
      assertThrowsExactly(
          ListSizeException.class,
          () -> algorithm.sort(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)),
          "The " + algorithm.getClass().getSimpleName() + " algorithm has crashed.");
    }
  }
}
