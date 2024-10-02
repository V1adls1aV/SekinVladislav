package base.animals.species;

import base.animals.Animal;
import base.animals.Overland;
import base.foods.vegetative.Grass;

public class Camel extends Animal implements Overland {
  public void eat(Grass food) {
    super.eat(food);
  }
}
