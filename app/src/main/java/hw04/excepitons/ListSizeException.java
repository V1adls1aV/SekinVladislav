package hw04.excepitons;

public class ListSizeException extends RuntimeException {
  public ListSizeException() {
    super();
  }

  public ListSizeException(String message) {
    super(message);
  }

  public ListSizeException(int length) {
    super(
        "The list ("
            + length
            + " elements) overflows the specified size threshold for this algorithm.");
  }
}
