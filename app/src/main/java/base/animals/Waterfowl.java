package base.animals;

public interface Waterfowl {
  public default void swim() {
    System.out.println(this.getClass().getSimpleName() + " is swimming.");
  }
}
