package hw06.db.factory;

import hw06.db.repository.article.ArticleRepository;
import hw06.db.repository.article.InMemoryArticleRepository;

/**
 * InMemory implementation yields the {@link InMemoryArticleRepository} especially.
 */
public class InMemoryDatabaseFactory implements DatabaseFactory {
  @Override
  public ArticleRepository getArticleRepository() {
    return new InMemoryArticleRepository();
  }
}
