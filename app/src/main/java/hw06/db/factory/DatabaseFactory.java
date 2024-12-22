package hw06.db.factory;

import hw06.core.settings.Settings;
import hw06.db.repository.article.ArticleRepository;

/**
 * Abstract factory for yielding whole family of the repositories.
 * Currently, has only one repository.
 */
public interface DatabaseFactory {
  ArticleRepository getArticleRepository();

  private static DatabaseFactory setupDatabaseFactory() {
    return switch (Settings.getDatabaseType()) {
      case IN_MEMORY -> new InMemoryDatabaseFactory();
      case POSTGRESQL -> throw new UnsupportedOperationException("PostgreSQL database is not implemented yet");
    };
  }

  static DatabaseFactory getInstance() {
    return setupDatabaseFactory();
  }
}
