package hw04.sorts;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hw04.excepitons.ListSizeException;
import hw04.sorts.algorithms.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Responsibility chain of sorts test. */
public class SortCompositionTest {
  @Test
  public void testSorting() {
    ArrayList<Integer> list = new ArrayList<>(List.of(8, 3, 2, 6, 4, 1, 5, 7));
    ArrayList<Integer> correct = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8));

    Sorter sorter = new Sorter(List.of(new BubbleSort(), new MergeSort()));
    ArrayList<Integer> result = sorter.sort(list);
    assertTrue(result.equals(correct));
  }

  @Test
  public void testFailingSorting() {
    Sorter sorter = new Sorter(List.of(new MergeSort(5), new BubbleSort(5)));
    assertThrowsExactly(
        ListSizeException.class, () -> sorter.sort(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
  }
}
