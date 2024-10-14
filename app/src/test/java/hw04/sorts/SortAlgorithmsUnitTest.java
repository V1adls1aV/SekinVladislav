package hw04.sorts;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Separate tests for each of the sort algorithms from hw04.sorts.algorithms. */
public class SortAlgorithmsUnitTest {
  protected static List<Integer> list = new ArrayList<>(List.of(8, 3, 2, 6, 4, 1, 5, 7));
  protected static List<Integer> correct = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
  protected static List<SortAlgorithm> algorithms =
      new ArrayList<>(List.of(new MergeSort(), new BubbleSort()));

  @Test
  public void testSorting() {
    for (SortAlgorithm algorithm : algorithms) {
      List<Integer> result = algorithm.sort(list);
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
          () -> algorithm.sort(list),
          "The " + algorithm.getClass().getSimpleName() + " algorithm has crashed.");
    }
  }

  @Test
  public void stressTestSorting() {
    List<Integer> list = new ArrayList<Integer>();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      list.add(random.nextInt(1000));
    }

    for (SortAlgorithm algorithm : algorithms) {
      List<Integer> result = algorithm.sort(list);

      for (int i = 0; i < result.size() - 1; i++) {
        assertTrue(result.get(i) <= result.get(i + 1));
      }
    }
  }
}
