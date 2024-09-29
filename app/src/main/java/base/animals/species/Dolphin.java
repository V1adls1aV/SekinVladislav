package base.animals.species;

import base.animals.Animal;
import base.animals.Waterfowl;
import base.foods.fish.Fish;

public class Dolphin extends Animal implements Waterfowl {
  public void eat(Fish food) {
    super.eat(food);
  }

  @Override
  public void swim() {
    System.out.println("Dolphin is swimming...");
  }
}
