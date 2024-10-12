package hw03;

import hw03.custom_ds.CustomArrayList;

public class Main {
  public static void main(String[] args) {
    CustomArrayList<Integer> list = new CustomArrayList<Integer>(2);
    System.out.println(list.isEmpty());

    try {
      list.get(0);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("There is no element with the specified index.");
    }

    for (int i = 0; i != 10; i++) {
      list.add(i);
    }
    System.out.println(list.isEmpty());

    for (int i = 0; i != list.size(); i++) {
      System.out.print(list.get(i) + " ");
    }
    System.out.println();

    System.out.println(list.size());
    System.out.println(list.get(5));
    System.out.println(list.get(-1));

    for (int i = 7; i != 4; i--) {
      list.remove(i);
    }

    for (int i = 0; i != list.size(); i++) {
      System.out.print(list.get(i) + " ");
    }
    System.out.println();
    System.out.println(list.size());
  }
}
