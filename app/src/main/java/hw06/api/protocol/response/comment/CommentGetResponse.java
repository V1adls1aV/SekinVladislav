package hw06.api.protocol.response.comment;

import hw06.dto.comment.Comment;

public record CommentGetResponse(long id, long articleId, String text) {
  public CommentGetResponse(Comment comment) {
    this(comment.id().id(), comment.articleId().id(), comment.text());
  }
}
