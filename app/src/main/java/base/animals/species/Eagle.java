package base.animals.species;

import base.animals.Animal;
import base.animals.Flying;
import base.foods.meat.Meat;

public class Eagle extends Animal implements Flying {
  public void eat(Meat food) {
    super.eat(food);
  }
}
