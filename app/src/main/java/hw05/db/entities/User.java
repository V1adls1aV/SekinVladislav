package hw05.db.entities;

/**
 * Entity representing a user.
 *
 * <p>Represents row of the database table. Any field can not be null.
 */
public class User {
  private String firstName;
  private String lastName;
  private String phoneNumber;

  /**
   * Constructor for User.
   *
   * @param firstName
   * @param lastName
   * @param phoneNumber
   * @throws IllegalArgumentException if any of the fields are null.
   */
  public User(String firstName, String lastName, String phoneNumber) {
    if (firstName == null || lastName == null || phoneNumber == null) {
      throw new IllegalArgumentException("User fields cannot be null.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @return True, if all fields are equal, false otherwise.
   */
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return user.firstName.equals(firstName)
        && user.lastName.equals(lastName)
        && user.phoneNumber.equals(phoneNumber);
  }

  @Override
  public String toString() {
    return "User("
        + "firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ')';
  }
}
