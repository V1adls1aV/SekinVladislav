package hw06.api.protocol.response.article;

import hw06.api.protocol.response.comment.CommentGetResponse;
import hw06.dto.article.Article;

import java.util.List;

public record ArticleGetResponse(long id, String title, List<String> tags, List<CommentGetResponse> comments) {
  public ArticleGetResponse(Article article) {
    this(article.id().id(), article.title(), article.tags().stream().toList(),
        article.comments().stream().map(CommentGetResponse::new).toList());
  }
}
