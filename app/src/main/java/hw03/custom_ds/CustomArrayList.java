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
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity must be non-negative");
    } else if (capacity == 0) {
      capacity = 1;
    }

    this.array = (T[]) new Object[capacity];
    this.capacity = capacity;
    this.size = 0;
  }

  protected int correctIndex(int index) throws IndexOutOfBoundsException {
    if (index < -size || size <= index) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
    }

    return (index + this.size) % this.size;
  }

  /**
   * Adds the specified element to the end of this custom array list.
   *
   * <p>This method increases the size of the custom array list by one and appends the specified
   * element at the end. If the internal array is full, it will be resized to accommodate the new
   * element.
   *
   * @param obj the element to be added to this custom array list
   * @throws NullPointerException if the specified element is null
   */
  public void add(T obj) throws NullPointerException {
    if (obj == null) {
      throw new NullPointerException("Null element is not allowed");
    }

    if (this.size == this.capacity) {
      this.capacity *= 2;
      T[] temp = (T[]) new Object[this.capacity];
      System.arraycopy(this.array, 0, temp, 0, this.size);
      this.array = temp;
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
    T value = array[idx];
    size--;

    for (int i = idx; i < size; i++) {
      array[i] = array[i + 1];
    }

    if (size <= capacity / 2) {
      capacity /= 2;
      T[] temp = (T[]) new Object[capacity];
      System.arraycopy(array, 0, temp, 0, size);
      array = temp;
    }

    return value;
  }

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }
}
