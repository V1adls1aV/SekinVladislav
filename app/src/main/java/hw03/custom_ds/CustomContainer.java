package hw03.custom_ds;

/**
 * # CustomCountainer
 *
 * <p>Interface with the next methods: add(T obj) get(int idx) remove(int idx)
 */
public interface CustomContainer<T> {
  /**
   * Adds an object of type <T> to the end of the container.
   *
   * @param obj
   */
  public void add(T obj);

  /**
   * Returns the object of type <T> at the specified index.
   *
   * @param idx
   * @return Object at the specified index
   */
  public T get(int idx);

  /**
   * Removes the object at the specified index and returns it.
   *
   * @param idx
   * @return Object at the specified index
   */
  public T remove(int idx);
}
