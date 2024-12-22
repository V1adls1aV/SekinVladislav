package hw06.dto;

import java.util.Comparator;

public class IdComparator implements Comparator<Id> {
  @Override
  public int compare(Id id1, Id id2) {
    return id1.compareTo(id2);
  }
}
