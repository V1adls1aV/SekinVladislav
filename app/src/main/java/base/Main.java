package base;

import base.animals.species.Camel;
import base.animals.species.Dolphin;
import base.animals.species.Eagle;
import base.animals.species.Horse;
import base.animals.species.Tiger;
import base.foods.fish.Fish;
import base.foods.grass.Grass;
import base.foods.meat.Beef;
import base.foods.meat.Meat;

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
