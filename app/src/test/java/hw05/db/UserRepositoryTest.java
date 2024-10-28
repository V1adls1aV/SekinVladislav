package hw05.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hw05.db.entities.User;
import hw05.db.repositories.user.UserRepository;
import hw05.db.repositories.user.UserRepositoryHashMap;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
  private static final User firstUser = new User("Vlad", "Sekin", "9336339");
  private static final User firstUserUpdated = new User("Vladislav", "Sekin", "9336339");
  private static final User secondUser = new User("Gleb", "Karpov", "8225228");

  @Test
  public void testInsert() {
    UserRepository userRepository = new UserRepositoryHashMap();

    userRepository.updateByMsisdn(firstUser);
    userRepository.updateByMsisdn(secondUser);

    User user = userRepository.findByMsisdn(firstUser.getMsisdn());
    assertEquals(firstUser, user);

    user = userRepository.findByMsisdn(secondUser.getMsisdn());
    assertEquals(secondUser, user);
  }

  @Test
  public void testUpdate() {
    UserRepository userRepository = new UserRepositoryHashMap();

    userRepository.updateByMsisdn(firstUser);
    userRepository.updateByMsisdn(firstUserUpdated);

    assertEquals(firstUserUpdated, userRepository.findByMsisdn(firstUser.getMsisdn()));
  }
}
