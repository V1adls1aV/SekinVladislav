package hw05.parser.enrichers;

import hw05.parser.messages.Message;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Message enricher implementation.
 *
 * <p>Enriches the given message with current datetime, if `timestamp` field is not set.
 */
public class DatetimeEnricher implements Enricher {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * Enriches the given message with current datetime, if `timestamp` field is not set.
   *
   * @param message to be enriched.
   */
  public void enrich(Message message) {
    if (!message.containsKey("timestamp")) {
      message.put("timestamp", LocalDateTime.now().format(FORMATTER));
    }
  }
}
