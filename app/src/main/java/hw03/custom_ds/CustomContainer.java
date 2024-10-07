package hw03.custom_ds;

/**
 * # CustomCountainer
 *
 * <p>Interface with the next methods: add(T obj) get(int idx) remove(int idx)
 */
public interface CustomContainer<T> {
  public void add(T obj);

  public T get(int idx);

  public T remove(int idx);
}
