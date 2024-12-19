package hw06.dto.comment;

import hw06.dto.article.ArticleId;

/**
 * Represents a comment for article with articleId.
 * May be used for displaying comments themselves or changing their content.
 */
public record Comment(CommentId id, ArticleId articleId, String text) {
  /**
   * Create a new instance with the specified text.
   * @param text to override with
   */
  public Comment withText(String text) {
    return new Comment(id, articleId, text);
  }

  /**
   * Create a new instance with the specified articleId.
   * @param articleId to override with
   */
  public Comment withArticleId(ArticleId articleId) {
    return new Comment(id, articleId, text);
  }
}
