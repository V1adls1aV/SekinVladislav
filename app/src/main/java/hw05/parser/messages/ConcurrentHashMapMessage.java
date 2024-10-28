package hw05.parser.messages;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapMessage extends ConcurrentHashMap<String, String> implements Message {
  public ConcurrentHashMapMessage(Map<String, String> message) {
    super(message);
  }

  public ConcurrentHashMapMessage() {
    super();
  }
}
