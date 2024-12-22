package hw06.core.exceptions.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleRowNotFoundException extends RuntimeException {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleRowNotFoundException.class);

  public ArticleRowNotFoundException() {
    super("Article with such id not found in the storage.");
    LOG.debug("Article with such id not found in the storage.");
  }
}
