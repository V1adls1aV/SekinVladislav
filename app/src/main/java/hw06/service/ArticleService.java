package hw06.service;

import hw06.core.exceptions.service.ArticleUpdateException;
import hw06.db.factory.DatabaseFactory;
import hw06.db.repository.article.ArticleRepository;
import hw06.dto.article.Article;
import hw06.dto.article.ArticleData;
import hw06.dto.article.ArticleId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class ArticleService {
  private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
  private static final ArticleRepository articleRepository = databaseFactory.getArticleRepository();

  private static final Logger LOG = LoggerFactory.getLogger(ArticleService.class);

  public static List<Article> getAll() {
    return articleRepository.getAll();
  }

  public static Optional<Article> getById(long articleId) {
    return articleRepository.getById(new ArticleId(articleId));
  }

  public static long create(String title, Set<String> tags) {
    Article article = articleRepository.create(new ArticleData(title, tags));
    return article.id().id();
  }

  public static void update(long articleId, String title, Set<String> tags) throws ArticleUpdateException {
    try {
      articleRepository.update(new ArticleId(articleId), new ArticleData(title, tags));
    } catch (ArticleUpdateException e) {
      LOG.warn("Error has occurred while updating article.", e);
      throw new ArticleUpdateException();
    }
  }

  public static void delete(long articleId) {
    articleRepository.delete(new ArticleId(articleId));
    LOG.info("Article with id={} has been deleted", articleId);
  }
}
