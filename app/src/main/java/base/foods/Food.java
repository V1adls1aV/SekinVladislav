package base.foods;

public abstract class Food {
  public String getName() {
    return this.getClass().getSimpleName().toLowerCase();
  }
}
