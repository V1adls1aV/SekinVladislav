package hw06.db.repository.article;

import hw06.core.exceptions.db.ArticleRowNotFoundException;
import hw06.core.exceptions.db.CommentRowNotFoundException;
import hw06.db.schema.article.InMemoryArticleSchema;
import hw06.db.schema.comment.InMemoryCommentSchema;
import hw06.dto.IdComparator;
import hw06.dto.article.Article;
import hw06.dto.article.ArticleData;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.Comment;
import hw06.dto.comment.CommentData;
import hw06.dto.comment.CommentId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory (temporary) storage for the articles.
 */
public class InMemoryArticleRepository implements ArticleRepository {
  private static final Map<ArticleId, InMemoryArticleSchema> storage = new ConcurrentHashMap<>();
  private static final AtomicLong articleCurrentId = new AtomicLong(1L);
  private static final AtomicLong commentCurrentId = new AtomicLong(1L);

  private static final Logger LOG = LoggerFactory.getLogger(InMemoryArticleRepository.class);

  /**
   * @return A new unique identifier for an article.
   */
  private static ArticleId generateArticleId() {
    return new ArticleId(articleCurrentId.getAndAdd(1L));
  }

  /**
   * @return A new unique identifier for a comment.
   */
  private static CommentId generateCommentId() {
    return new CommentId(commentCurrentId.getAndAdd(1L));
  }

  public Article create(ArticleData article) {
    ArticleId newId = generateArticleId();
    InMemoryArticleSchema newArticle = new InMemoryArticleSchema(
        newId, article.title(), article.tags(), new ConcurrentSkipListMap<>(new IdComparator())
    );
    storage.put(newId, newArticle);

    LOG.info("Created new article: {}", newArticle);
    return newArticle.toArticle();
  }

  public Optional<Article> getById(ArticleId id) {
    InMemoryArticleSchema article = storage.get(id);
    if (article == null) return Optional.empty();
    return Optional.of(article.toArticle());
  }

  public List<Article> getAll() {
    return storage.values().stream().map(InMemoryArticleSchema::toArticle).toList();
  }

  public void update(ArticleId articleId, ArticleData articleData) throws ArticleRowNotFoundException {
    InMemoryArticleSchema article = storage.get(articleId);
    if (article == null) throw new ArticleRowNotFoundException();
    article.setTitleAndTags(
        articleData.title(),
        articleData.tags());
    LOG.info("Article with id={} has been updated with fields {}", articleId, articleData);
  }

  public void delete(ArticleId id) {
    storage.remove(id);
    LOG.info("Article with id={} has been deleted", id);
  }

  public Comment addComment(ArticleId articleId, CommentData commentData) throws ArticleRowNotFoundException {
    InMemoryArticleSchema article = storage.get(articleId);
    if (article == null) throw new ArticleRowNotFoundException();
    CommentId newCommentId = generateCommentId();
    InMemoryCommentSchema newComment = new InMemoryCommentSchema(
        newCommentId, articleId, commentData.text()
    );

    article.getComments().put(newCommentId, newComment);
    LOG.info("Added new comment {} to article with id={}", newComment.toComment(), articleId);
    return newComment.toComment();
  }

  public void updateComment(ArticleId articleId, CommentId commentId, CommentData commentData) throws ArticleRowNotFoundException, CommentRowNotFoundException {
    InMemoryArticleSchema article = storage.get(articleId);
    if (article == null) throw new ArticleRowNotFoundException();

    InMemoryCommentSchema comment = article.getComments().get(commentId);
    if (comment == null) throw new CommentRowNotFoundException();

    comment.setText(commentData.text());
    LOG.info("Comment with id={} from article id={} has been updated with fields {}", commentId, articleId, commentData);
  }

  public void deleteComment(ArticleId articleId, CommentId commentId) {
    InMemoryArticleSchema article = storage.get(articleId);
    if (article == null) {
      LOG.info("The article id={} has been already removed. Comment with id={} does not exist.", articleId, commentId);
      return;
    }
    article.getComments().remove(commentId);
    LOG.info("Comment with id={} from article id={} has been removed", commentId, articleId);
  }
}
