package hw05.db.repository;

import hw05.db.entities.User;
import hw05.db.exceptions.UniqueValueException;
import java.util.HashMap;

/**
 * Repository implementation for User entities.
 *
 * <p>Temporary entities are stored in HashMap (with phone number key) instead of database.
 */
public class UserRepository {
  private HashMap<String, User> users = new HashMap<>();

  /**
   * Add a new user to the repository.
   *
   * @param user
   * @throws UniqueValueException if user with the same phone number already exists.
   */
  public void addUser(User user) throws UniqueValueException {
    if (users.containsKey(user.getPhoneNumber())) {
      throw new UniqueValueException(user);
    }
    users.put(user.getPhoneNumber(), user);
  }

  /**
   * Get user by phone number.
   *
   * @param phoneNumber
   * @return User or null if not found.
   */
  public User getUserByPhoneNumber(String phoneNumber) {
    return users.get(phoneNumber);
  }
}
