package hw05.db.repositories.user;

import hw05.db.entities.User;

/**
 * Repository interface for User entities.
 *
 * <p>Interface defines CRUD operations for User entities.
 */
public interface UserRepository {

  /**
   * Find a user by its msisdn.
   *
   * @param msisdn
   * @return User with the specified phone number or null if not found.
   */
  User findByMsisdn(String msisdn);

  /**
   * Find and update an existing user by msisdn.
   *
   * @param user User to insert or to update.
   */
  void updateByMsisdn(User user);
}
