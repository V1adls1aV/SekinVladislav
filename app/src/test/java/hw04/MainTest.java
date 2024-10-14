package hw04;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  public void test() {
    ArrayList<Integer> result = Main.generateSorted();

    for (int i = 0; i < result.size() - 1; i++) {
      assertTrue(result.get(i) <= result.get(i + 1));
    }
  }
}
