package hw05.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import hw05.parser.enrichers.DatetimeEnricher;
import hw05.parser.enrichers.Enricher;
import hw05.parser.messages.ConcurrentHashMapMessage;
import hw05.parser.messages.Message;
import org.junit.jupiter.api.Test;

public class TimeStampEnricherTest {
  @Test
  public void testEnrichWithTimestamp() {
    Enricher datetimeEnricher = new DatetimeEnricher();

    Message message = new ConcurrentHashMapMessage();
    message.put("movie", "Treasure Island");
    datetimeEnricher.enrich(message);

    assertEquals("Treasure Island", message.get("movie"));
    assertNotNull(message.get("timestamp"));
  }

  @Test
  public void testEnrichNothingTimestamp() {
    Enricher datetimeEnricher = new DatetimeEnricher();

    Message message = new ConcurrentHashMapMessage();
    message.put("movie", "Treasure Island");
    message.put("timestamp", "2021-10-10 10:10:10");
    datetimeEnricher.enrich(message);

    assertEquals("Treasure Island", message.get("movie"));
    assertEquals("2021-10-10 10:10:10", message.get("timestamp"));
  }
}
