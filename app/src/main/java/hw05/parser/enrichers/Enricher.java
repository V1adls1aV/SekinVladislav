package hw05.parser.enrichers;

import hw05.parser.messages.Message;

/**
 * Message enricher. Modifies the reference of the `message`, so returns nothing.
 *
 * <p>Adds fields to a `message` if certain conditions are met (like existance of specific fields).
 */
public interface Enricher {

  /**
   * Enriches the reference of given message.
   *
   * @param message to be enriched.
   */
  void enrich(Message message);
}
