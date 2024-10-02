package base.animals;

import base.foods.Food;

public abstract class Animal {
  public void eat(Food food) {
    System.out.println(this.getClass().getSimpleName() + " eats " + food.getName() + ".");
  }
}
