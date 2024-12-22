package hw06.service;

import hw06.core.exceptions.db.ArticleRowNotFoundException;
import hw06.core.exceptions.db.CommentRowNotFoundException;
import hw06.core.exceptions.service.ArticleNotFoundException;
import hw06.core.exceptions.service.CommentNotFoundException;
import hw06.db.factory.DatabaseFactory;
import hw06.db.repository.article.ArticleRepository;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.Comment;
import hw06.dto.comment.CommentData;
import hw06.dto.comment.CommentId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommentService {
  private static final DatabaseFactory databaseFactory = DatabaseFactory.getInstance();
  private static final ArticleRepository articleRepository = databaseFactory.getArticleRepository();

  private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

  public static long create(long articleId, String content) throws ArticleNotFoundException {
    try {
      Comment comment = articleRepository.addComment(
          new ArticleId(articleId),
          new CommentData(content)
      );
      return comment.id().id();

    } catch (ArticleRowNotFoundException e) {
      throw new ArticleNotFoundException();
    }
  }

  public static void update(long articleId, long commentId, String content) throws ArticleNotFoundException, CommentNotFoundException {
    try {
      articleRepository.updateComment(
          new ArticleId(articleId),
          new CommentId(commentId),
          new CommentData(content)
      );
    } catch (ArticleRowNotFoundException e) {
      LOG.warn("The article related to the comment does not exist.", e);
      throw new ArticleNotFoundException();
    } catch (CommentRowNotFoundException e) {
      LOG.warn("The comment related to the article does not exist.", e);
      throw new CommentNotFoundException();
    }
  }

  public static void delete(long articleId, long commentId) {
    articleRepository.deleteComment(
        new ArticleId(articleId),
        new CommentId(commentId)
    );
  }
}