package base;

import base.animals.species.Camel;
import base.animals.species.Dolphin;
import base.animals.species.Eagle;
import base.animals.species.Horse;
import base.animals.species.Tiger;
import base.foods.marine.Fish;
import base.foods.meat.Beef;
import base.foods.meat.Meat;
import base.foods.vegetative.Grass;

public class Main {
  public static void main(String[] args) {
    Camel camel = new Camel();
    camel.eat(new Grass());
    camel.walk();

    Dolphin dolphin = new Dolphin();
    dolphin.eat(new Fish());
    dolphin.swim();

    Eagle eagle = new Eagle();
    eagle.eat(new Meat());
    eagle.fly();

    Horse horse = new Horse();
    horse.eat(new Grass());
    horse.walk();

    Tiger tiger = new Tiger();
    tiger.eat(new Beef());
    tiger.walk();
  }
}
