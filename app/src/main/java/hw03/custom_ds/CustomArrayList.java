package hw03.custom_ds;

/**
 * # CustomArrayList
 *
 * <p>Custom dynamic array implementation with reversed indexing.
 *
 * <p>Operations:
 *
 * <table>
 *   <tr><th>Case</th><th>get</th><th>add</th><th>remove</th></tr>
 *   <tr><td>Good</td><td>O(1)</td><td>O(1)</td><td>O(1)</td></tr>
 *   <tr><td>Mean</td><td>O(1)</td><td>O(1)</td><td>O(n)</td></tr>
 *   <tr><td>Bad</td><td>O(1)</td><td>O(n)</td><td>O(n)</td></tr>
 * </table>
 */
public class CustomArrayList<T> implements CustomContainer<T> {
  protected T[] array;
  protected int capacity;
  protected int size;

  /**
   * Constructor for custom array list that reserves memory for N elements. The capacity != size.
   * Size is 0 by default.
   *
   * @param capacity a reserved size for the array
   */
  public CustomArrayList(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive.");
    }

    this.array = (T[]) new Object[capacity];
    this.capacity = capacity;
    this.size = 0;
  }

  /**
   * Corrects the index for reversed indexing.
   *
   * <p>This method takes an index as input and returns the corresponding index in the internal
   * array. If the provided index is out of bounds, an {@link IndexOutOfBoundsException} is thrown.
   *
   * @param index the index to be corrected
   * @return the corrected index in the internal array
   * @throws IndexOutOfBoundsException if index out of bounds, you may specify reversed indexing
   */
  protected int correctIndex(int index) throws IndexOutOfBoundsException {
    if (index < -size || size <= index) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    return (index + this.size) % this.size;
  }

  protected void changeCapacity(int newCapacity) {
    T[] temp = (T[]) new Object[newCapacity];
    System.arraycopy(this.array, 0, temp, 0, this.size);
    this.array = temp;
    this.capacity = newCapacity;
  }

  /**
   * Performs a left shift operation on the elements of the internal array starting from the
   * specified index. This method is used to remove an element from the custom array list by
   * shifting all subsequent elements to the left.
   *
   * @param from the index from which the left shift operation should start. The element at this
   *     index will be overwritten by the next element, and so on
   * @throws IndexOutOfBoundsException if index out of bounds, you may specify reversed indexing
   */
  protected void leftShiftElements(int from) throws IndexOutOfBoundsException {
    from = correctIndex(from);

    this.size--;
    for (int i = from; i < this.size; i++) {
      this.array[i] = this.array[i + 1];
    }
  }

  /**
   * Adds the specified element to the end of this custom array list.
   *
   * <p>This method increases the size of the custom array list by one and appends the specified
   * element at the end. If the internal array is full, it will be resized to accommodate the new
   * element.
   *
   * @param obj the element to be added to this custom array list
   * @throws IllegalArgumentException if the specified element is null
   */
  public void add(T obj) throws IllegalArgumentException {
    if (obj == null) {
      throw new IllegalArgumentException("Null element is not allowed");
    }

    if (this.size == this.capacity) {
      changeCapacity(this.capacity * 2);
    }

    this.array[this.size] = obj;
    this.size++;
  }

  /**
   * Get element by its index.
   *
   * @param idx the index of the desired element
   * @return the element with index = idx
   * @throws IndexOutOfBoundsException if index out of bounds, you may specify reversed indexing
   */
  public T get(int idx) throws IndexOutOfBoundsException {
    idx = this.correctIndex(idx);
    return array[idx];
  }

  /**
   * Removes element from custom array list by index and returns it.
   *
   * @param idx the index of the element to be removed
   * @return the removed element
   * @throws IndexOutOfBoundsException if index out of bounds, you may specify reversed indexing
   */
  public T remove(int idx) throws IndexOutOfBoundsException {
    idx = this.correctIndex(idx);

    T value = this.array[idx];

    leftShiftElements(idx);

    if (this.size <= this.capacity / 2) {
      changeCapacity(this.capacity / 2);
    }

    return value;
  }

  /**
   * Size of the custom array list.
   *
   * @return the number of elements in this custom array list
   */
  public int size() {
    return this.size;
  }

  /**
   * Checks if the custom array list is empty.
   *
   * @return {@code true} if the custom array list contains no elements; {@code false} otherwise.
   */
  public boolean isEmpty() {
    return this.size == 0;
  }
}
