package hw06.db.repository.article;

import hw06.db.schema.article.InMemoryArticleSchema;
import hw06.db.schema.comment.InMemoryCommentSchema;
import hw06.dto.article.Article;
import hw06.dto.article.ArticleData;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.Comment;
import hw06.dto.comment.CommentData;
import hw06.dto.comment.CommentId;

import java.util.*;
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
        newId, article.title(), article.tags(), new ConcurrentSkipListMap<>()
    );
    storage.put(newId, newArticle);
    return newArticle.toArticle();
  }

  public Optional<Article> getById(ArticleId id) {
    return Optional.of(storage.get(id).toArticle());
  }

  public void update(ArticleId articleId, ArticleData articleData) {
    storage.get(articleId).setTitleAndTags(
        articleData.title(),
        articleData.tags());
  }

  public void delete(ArticleId id) {
    storage.remove(id);
  }

  public Comment addComment(ArticleId articleId, CommentData commentData) {
    InMemoryArticleSchema article = storage.get(articleId);
    CommentId newCommentId = generateCommentId();
    InMemoryCommentSchema newComment = new InMemoryCommentSchema(
        newCommentId, articleId, commentData.text()
    );

    article.getComments().put(newCommentId, newComment);
    return newComment.toComment();
  }

  public void updateComment(ArticleId articleId, CommentId commentId, CommentData commentData) {
    InMemoryArticleSchema article = storage.get(articleId);
    article.getComments().get(commentId).setText(commentData.text());
  }

  public void removeComment(ArticleId articleId, CommentId commentId) {
    storage.get(articleId).getComments().remove(commentId);
  }
}
