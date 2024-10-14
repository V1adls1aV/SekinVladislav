package hw04.excepitons;

/** Exception to be thrown when the size of the list exceeds the allowed threshold. */
public class ListSizeException extends RuntimeException {
  public ListSizeException() {
    super();
  }

  /**
   * Creates exception message with given error message.
   *
   * @param message the error message
   */
  public ListSizeException(String message) {
    super(message);
  }

  /**
   * Creates basic exception message with given array length.
   *
   * @param length the length of the list
   */
  public ListSizeException(int length) {
    super(
        "The list ("
            + length
            + " elements) overflows the specified size threshold for this algorithm.");
  }
}
