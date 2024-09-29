package base.animals.species;

import base.animals.Animal;
import base.animals.Overland;
import base.foods.meat.Beef;

public class Tiger extends Animal implements Overland {
  public void eat(Beef food) {
    super.eat(food);
  }

  @Override
  public void walk() {
    System.out.println("Tiger is walking...");
  }
}
