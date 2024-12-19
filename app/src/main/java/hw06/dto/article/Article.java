package hw06.dto.article;

import java.util.Set;
import java.util.List;

import hw06.dto.comment.Comment;

/**
 * Represents an article. May be used for displaying articles themselves or changing their content.
 * <p>
 * Has methods for creating a new instances with different fields.
 */
public record Article(ArticleId id, String title, Set<String> tags, List<Comment> comments) {
  /**
   * Create a new instance with the specified title.
   * @param title to override with
   */
  public Article withTitle(String title) {
    return new Article(id, title, tags, comments);
  }

  /**
   * Create a new instance with the specified tags.
   * @param tags to override with
   */
  public Article withTags(Set<String> tags) {
    return new Article(id, title, tags, comments);
  }

  /**
   * Create a new instance with the specified comments.
   * @param comments to override with
   */
  public Article withComments(List<Comment> comments) {
    return new Article(id, title, tags, comments);
  }
}
