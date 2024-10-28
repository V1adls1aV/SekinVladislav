package hw05.db.entities;

/**
 * Entity representing a user.
 *
 * <p>Represents row of the database table. Any field can not be null.
 */
public class User {
  private String firstName;
  private String lastName;
  private String msisdn;

  /**
   * Constructor for User.
   *
   * @param firstName
   * @param lastName
   * @param msisdn
   * @throws IllegalArgumentException if any of the fields are null.
   */
  public User(String firstName, String lastName, String msisdn) {
    if (firstName == null || lastName == null || msisdn == null) {
      throw new IllegalArgumentException("User fields cannot be null.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.msisdn = msisdn;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMsisdn() {
    return msisdn;
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
    return user.firstName.equals(this.firstName)
        && user.lastName.equals(this.lastName)
        && user.msisdn.equals(this.msisdn);
  }

  @Override
  public String toString() {
    return "User("
        + "firstName='"
        + this.firstName
        + '\''
        + ", lastName='"
        + this.lastName
        + '\''
        + ", phoneNumber='"
        + this.msisdn
        + '\''
        + ')';
  }
}
