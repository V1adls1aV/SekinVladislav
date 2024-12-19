package hw06.db.repository.article;

import hw06.dto.article.Article;
import hw06.dto.article.ArticleData;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.Comment;
import hw06.dto.comment.CommentData;
import hw06.dto.comment.CommentId;

import java.util.Optional;


/**
 * Repository for articles with comments.
 */
public interface ArticleRepository {
  /**
   * Returns the instance of the new article created
   *
   * @param article the data from which the new article will be created
   * @return the instance of the new article with unique id
   */
  Article create(ArticleData article);

  /**
   * Returns the article with the given id
   *
   * @param id the id of the article to retrieve
   * @return the article with the given id
   */
  Optional<Article> getById(ArticleId id);

  /**
   * Overrides fields of article with specified id.
   * Does not update the comments. Use separate methods for such.
   *
   * @param articleId the id of the article
   * @param articleData – the data to update
   */
  void update(ArticleId articleId, ArticleData articleData);

  /**
   * Deletes article with the given id if such exists.
   * Deletes related comments too.
   *
   * @param id the id of the article to delete
   */
  void delete(ArticleId id);

  /**
   * Adds a comment to the article with the given id.
   *
   * @param articleId the id of the article
   * @param commentData the comment data from which create a new comment and add it
   * @return new created comment
   */
  Comment addComment(ArticleId articleId, CommentData commentData);

  /**
   * Updates a comment in the article with given data.
   *
   * @param articleId the id of the article the comment is from
   * @param commentId the id of the comment to be updated
   * @param commentData the data to update in the comment
   */
  void updateComment(ArticleId articleId, CommentId commentId, CommentData commentData);

  /**
   * Removes a comment from the article with the given id.
   * If the comment does not exist – does nothing.
   *
   * @param articleId the id of the article from which the comment will be removed
   * @param commentId the id of the comment to be removed
   */
  void removeComment(ArticleId articleId, CommentId commentId);
}
