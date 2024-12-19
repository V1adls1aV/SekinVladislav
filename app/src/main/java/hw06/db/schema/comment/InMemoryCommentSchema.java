package hw06.db.schema.comment;

import hw06.dto.article.ArticleId;
import hw06.dto.comment.Comment;
import hw06.dto.comment.CommentId;

public class InMemoryCommentSchema {
  private final CommentId id;
  private final ArticleId articleId;
  private String text;

  public InMemoryCommentSchema(CommentId id, ArticleId articleId, String text) {
    this.id = id;
    this.articleId = articleId;
    this.text = text;
  }

  public Comment toComment() {
    return new Comment(id, articleId, text);
  }

  public CommentId getId() {
    return id;
  }

  public ArticleId getArticleId() {
    return articleId;
  }

  public String getText() {
    return text;
  }

  public synchronized void setText(String text) {
    this.text = text;
  }
}
