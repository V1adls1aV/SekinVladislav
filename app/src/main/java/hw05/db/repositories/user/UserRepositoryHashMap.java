package hw05.db.repositories.user;

import hw05.db.entities.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository implementation for User entities.
 *
 * <p>Temporary entities are stored in ConcurrentHashMap (with phone number key) instead of
 * database.
 */
public class UserRepositoryHashMap implements UserRepository {
  private Map<String, User> users = new ConcurrentHashMap<>();

  /**
   * Rewrite a new user to the repository.
   *
   * @param user
   */
  public void updateByMsisdn(User user) {
    this.users.put(user.getMsisdn(), user);
  }

  /**
   * Get user by phone number.
   *
   * @param msisnd user phone number.
   * @return User or null if not found.
   */
  public User findByMsisdn(String msisdn) {
    return this.users.get(msisdn);
  }
}
