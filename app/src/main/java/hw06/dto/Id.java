package hw06.dto;

public interface Id extends Comparable<Id> {
  long id();

  default int compareTo(Id other) {
    return Long.compare(this.id(), other.id());
  }
}
