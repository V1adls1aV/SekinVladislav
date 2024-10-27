package hw05.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import hw05.db.entities.User;
import hw05.db.exceptions.UniqueValueException;
import hw05.db.repository.UserRepository;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
  @Test
  public void testCRUD() {
    UserRepository userRepository = new UserRepository();

    User firstUser = new User("Vladislav", "Sekin", "9336339");
    userRepository.addUser(firstUser);

    User secondUser = new User("Gleb", "Karpov", "8225228");
    userRepository.addUser(secondUser);

    User user = userRepository.getUserByPhoneNumber("9336339");
    assertEquals(firstUser, user);

    user = userRepository.getUserByPhoneNumber("8225228");
    assertEquals(secondUser, user);
  }

  @Test
  public void testUniqueConstraint() {
    hw05.db.repository.UserRepository userRepository = new UserRepository();

    User firstUser = new User("Vladislav", "Sekin", "9336339");
    userRepository.addUser(firstUser);

    assertThrowsExactly(
        UniqueValueException.class,
        () -> userRepository.addUser(new User("Gleb", "Karpov", "9336339")));
  }
}
