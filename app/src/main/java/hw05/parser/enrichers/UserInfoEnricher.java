package hw05.parser.enrichers;

import hw05.db.entities.User;
import hw05.db.repositories.user.UserRepository;
import hw05.parser.messages.Message;

/**
 * Message enricher implementation.
 *
 * <p>Enriches the given message with user information (`firstName` & `lastName`), if `msisdn` field
 * is set. Otherwise, does nothing.
 */
public class UserInfoEnricher implements Enricher {
  private final UserRepository userRepository;

  /**
   * Constructs a new UserInfoEnricher with the given UserRepository.
   *
   * @param userRepository the source of the information to enrich the message with.
   */
  public UserInfoEnricher(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Enriches the given message with user information (`firstName` & `lastName`), if `msisdn` field
   * is set. Otherwise, does nothing.
   *
   * @param message to be enriched.
   */
  public void enrich(Message message) {
    if (message.containsKey("msisdn")) {
      User user = this.userRepository.findByMsisdn(message.get("msisdn"));
      message.put("firstName", user.getFirstName());
      message.put("lastName", user.getLastName());
    }
  }
}
