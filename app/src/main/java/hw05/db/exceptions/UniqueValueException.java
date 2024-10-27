package hw05.db.exceptions;

/** General class for conflict in unique fields in the database. */
public class UniqueValueException extends RuntimeException {

  /**
   * Constructor for UniqueValueException.
   *
   * @param object specify the object represents a table in which conflict occurs.
   */
  public UniqueValueException(Object object) {
    super("Conflict with unique value constraint for " + object.getClass().getSimpleName() + ".");
  }
}
