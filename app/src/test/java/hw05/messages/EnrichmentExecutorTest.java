package hw05.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import hw05.db.entities.User;
import hw05.db.repositories.user.UserRepository;
import hw05.db.repositories.user.UserRepositoryHashMap;
import hw05.parser.enrichers.DatetimeEnricher;
import hw05.parser.enrichers.Enricher;
import hw05.parser.enrichers.EnrichmentExecutor;
import hw05.parser.enrichers.UserInfoEnricher;
import hw05.parser.messages.ConcurrentHashMapMessage;
import hw05.parser.messages.Message;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnrichmentExecutorTest {
  private static final User user = new User("John", "Doe", "123");
  private static UserRepository userRepository = new UserRepositoryHashMap();
  private static Enricher userInfoEnricher = new UserInfoEnricher(userRepository);
  private static Enricher datetimeEnricher = new DatetimeEnricher();

  @BeforeEach
  void setupUserRepository() {
    userRepository.updateByMsisdn(user);
  }

  @Test
  public void testSingleEnricher() {
    EnrichmentExecutor executor = new EnrichmentExecutor(List.of(userInfoEnricher));
    Message message = new ConcurrentHashMapMessage();
    message.put("msisdn", user.getMsisdn());
    message.put("movie", "Treasure Island");
    Message newMessage = executor.enrich(message);

    assertEquals("Treasure Island", newMessage.get("movie"));
    assertEquals(user.getMsisdn(), newMessage.get("msisdn"));
    assertEquals(user.getFirstName(), newMessage.get("firstName"));
    assertEquals(user.getLastName(), newMessage.get("lastName"));
    assertNull(newMessage.get("timestamp"));
  }

  @Test
  public void testMultipleEnrichers() {
    EnrichmentExecutor executor =
        new EnrichmentExecutor(List.of(userInfoEnricher, datetimeEnricher));
    Message message = new ConcurrentHashMapMessage();
    message.put("msisdn", user.getMsisdn());
    message.put("movie", "Treasure Island");
    Message newMessage = executor.enrich(message);

    assertEquals("Treasure Island", newMessage.get("movie"));
    assertEquals(user.getMsisdn(), newMessage.get("msisdn"));
    assertEquals(user.getFirstName(), newMessage.get("firstName"));
    assertEquals(user.getLastName(), newMessage.get("lastName"));
    assertNotNull(newMessage.get("timestamp"));
  }
}
