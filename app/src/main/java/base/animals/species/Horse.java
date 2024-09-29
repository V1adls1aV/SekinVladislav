package base.animals.species;

import base.animals.Animal;
import base.animals.Overland;
import base.foods.grass.Grass;

public class Horse extends Animal implements Overland {
  public void eat(Grass food) {
    super.eat(food);
  }

  @Override
  public void walk() {
    System.out.println("Horse is walking...");
  }
}
