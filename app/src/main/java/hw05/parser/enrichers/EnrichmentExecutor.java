package hw05.parser.enrichers;

import hw05.parser.messages.ConcurrentHashMapMessage;
import hw05.parser.messages.Message;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Service for enriching messages with various enrichers.
 *
 * <p>If some enrichers are not applicable to the given message, they will be ignored.
 */
public class EnrichmentExecutor {
  private final List<Enricher> enrichers;

  /**
   * Initializes a new EnrichmentService with the given enrichers.
   *
   * @param enrichers to be used for enriching messages.
   */
  public EnrichmentExecutor(List<Enricher> enrichers) {
    this.enrichers = enrichers;
  }

  /**
   * Enriches the given message with all enrichers.
   *
   * @param message to be enriched.
   * @return Enriched message.
   */
  public Message enrich(Message message) {
    Message newMessage = new ConcurrentHashMapMessage(message);

    CompletableFuture<Void> allOf =
        CompletableFuture.allOf(
            enrichers.stream()
                .map(
                    enricher ->
                        CompletableFuture.runAsync(
                            () -> {
                              enricher.enrich(newMessage);
                            }))
                .toArray(CompletableFuture[]::new));

    allOf.join();

    return newMessage;
  }
}
