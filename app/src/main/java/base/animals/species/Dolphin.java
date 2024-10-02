package base.animals.species;

import base.animals.Animal;
import base.animals.Waterfowl;
import base.foods.marine.Fish;

public class Dolphin extends Animal implements Waterfowl {
  public void eat(Fish food) {
    super.eat(food);
  }
}
