package hw06.db.schema.article;

import hw06.db.schema.comment.InMemoryCommentSchema;
import hw06.dto.article.Article;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.CommentId;

import java.util.Map;
import java.util.Set;

/**
 * Represents an in-memory schema for storing articles.
 */
public class InMemoryArticleSchema {
  private final ArticleId id;
  private String title;
  private Set<String> tags;
  private final Map<CommentId, InMemoryCommentSchema> comments;

  public InMemoryArticleSchema(
      ArticleId id, String title, Set<String> tags, Map<CommentId, InMemoryCommentSchema> comments) {
    this.id = id;
    this.title = title;
    this.tags = tags;
    this.comments = comments;
  }

  public Article toArticle() {
    return new Article(id, title, tags,
        comments.values().stream().map(
            InMemoryCommentSchema::toComment).toList());
  }

  public ArticleId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Set<String> getTags() {
    return tags;
  }

  public Map<CommentId, InMemoryCommentSchema> getComments() {
    return comments;
  }

  public synchronized void setTitle(String title) {
    this.title = title;
  }

  public synchronized void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public synchronized void setTitleAndTags(String title, Set<String> tags) {
    this.title = title;
    this.tags = tags;
  }
}
