package hw06.core.exceptions.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentRowNotFoundException extends RuntimeException {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleRowNotFoundException.class);

  public CommentRowNotFoundException() {
    super("Comment with such id not found in the storage.");
    LOG.debug("Comment with such id not found in the storage.");
  }
}
