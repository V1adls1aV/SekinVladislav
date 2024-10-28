package hw05.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import hw05.db.entities.User;
import hw05.db.repositories.user.UserRepository;
import hw05.db.repositories.user.UserRepositoryHashMap;
import hw05.parser.enrichers.Enricher;
import hw05.parser.enrichers.UserInfoEnricher;
import hw05.parser.messages.ConcurrentHashMapMessage;
import org.junit.jupiter.api.Test;

public class UserInfoEnricherTest {
  private static final User user = new User("123", "John", "Doe");

  @Test
  public void testEnrichWithUserHashMapRepository() {
    UserRepository userRepository = new UserRepositoryHashMap();
    Enricher userInfoEnricher = new UserInfoEnricher(userRepository);
    userRepository.updateByMsisdn(user);

    ConcurrentHashMapMessage message = new ConcurrentHashMapMessage();
    message.put("msisdn", user.getMsisdn());
    message.put("movie", "Treasure Island");
    userInfoEnricher.enrich(message);

    assertEquals("Treasure Island", message.get("movie"));
    assertEquals(user.getMsisdn(), message.get("msisdn"));
    assertEquals(user.getFirstName(), message.get("firstName"));
    assertEquals(user.getLastName(), message.get("lastName"));
  }

  @Test
  public void testEnrichNothingWithUserHashMapRepository() {
    UserRepository userRepository = new UserRepositoryHashMap();
    Enricher userInfoEnricher = new UserInfoEnricher(userRepository);
    userRepository.updateByMsisdn(new User("123", "John", "Doe"));

    ConcurrentHashMapMessage message = new ConcurrentHashMapMessage();
    message.put("movie", "Treasure Island");
    userInfoEnricher.enrich(message);

    assertEquals("Treasure Island", message.get("movie"));
    assertNull(message.get("msisdn"));
    assertNull(message.get("firstName"));
    assertNull(message.get("lastName"));
  }
}
