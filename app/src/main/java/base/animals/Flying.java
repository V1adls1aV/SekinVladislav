package base.animals;

public interface Flying {
  public default void fly() {
    System.out.println(this.getClass().getSimpleName() + " is flying.");
  }
}
