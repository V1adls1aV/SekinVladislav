package base.animals;

public interface Overland {
  public default void walk() {
    System.out.println(this.getClass().getSimpleName() + " is walking.");
  }
}
