package hw04.sorts;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Responsibility chain of sorts test. */
public class SortIntegrationTest {
  protected static List<Integer> list = new ArrayList<>(List.of(8, 3, 2, 6, 4, 1, 5, 7));
  protected static List<Integer> correct = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));
  protected static int overflowListSize = 5;

  @Test
  public void testSorting() {
    Sorter sorter = new Sorter(List.of(new BubbleSort(), new MergeSort()));
    List<Integer> result = sorter.sort(list);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testSortingTypeWithBroken1() {
    Sorter sorter = new Sorter(List.of(new BubbleSort(), new MergeSort(5), new BubbleSort(5)));
    List<Integer> result = sorter.sort(list, SortType.BUBBLE);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testSortingTypeWithBroken2() {
    Sorter sorter = new Sorter(List.of(new BubbleSort(5), new MergeSort(), new BubbleSort()));
    List<Integer> result = sorter.sort(list, SortType.MERGE);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testSortingWithBroken1() {
    Sorter sorter = new Sorter(List.of(new BubbleSort(overflowListSize), new MergeSort()));
    List<Integer> result = sorter.sort(list);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testSortingWithBroken2() {
    Sorter sorter = new Sorter(List.of(new BubbleSort(), new MergeSort(overflowListSize)));
    List<Integer> result = sorter.sort(list);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testFailingSorting() {
    Sorter sorter =
        new Sorter(List.of(new MergeSort(overflowListSize), new BubbleSort(overflowListSize)));
    assertThrowsExactly(ListSizeException.class, () -> sorter.sort(list));
  }

  @Test
  public void testFailingSortingType() {
    Sorter sorter =
        new Sorter(
            List.of(
                new MergeSort(overflowListSize),
                new BubbleSort(overflowListSize),
                new BubbleSort(overflowListSize)));
    assertThrowsExactly(ListSizeException.class, () -> sorter.sort(list, SortType.BUBBLE));
  }

  public void stressTestSorting() {
    List<Integer> list = new ArrayList<Integer>();
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      list.add(random.nextInt(1000));
    }

    Sorter sorter =
        new Sorter(
            List.of(new BubbleSort(50), new MergeSort(70), new MergeSort(100), new BubbleSort()));
    List<Integer> result = sorter.sort(list, SortType.MERGE);

    for (int i = 0; i < result.size() - 1; i++) {
      assertTrue(result.get(i) <= result.get(i + 1));
    }
  }
}
