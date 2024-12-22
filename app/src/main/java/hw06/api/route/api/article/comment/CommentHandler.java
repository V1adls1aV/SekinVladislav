package hw06.api.route.api.article.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw06.api.protocol.request.comment.CommentCreateRequest;
import hw06.api.protocol.response.ErrorResponse;
import hw06.api.protocol.response.comment.CommentCreateResponse;
import hw06.api.route.Handler;
import hw06.core.exceptions.service.ArticleNotFoundException;
import hw06.core.exceptions.service.CommentNotFoundException;
import hw06.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;


public final class CommentHandler implements Handler {
  private static final Logger LOG = LoggerFactory.getLogger(CommentHandler.class);

  private final String routePrefix;
  private final Service service;
  private final ObjectMapper objectMapper;

  public CommentHandler(String routePrefix, Service service, ObjectMapper objectMapper) {
    if (!routePrefix.contains("/:articleId")) {
      throw new IllegalArgumentException(
          "Invalid route prefix for comment handler. It must contain an articleId parameter.");
    }

    this.routePrefix = routePrefix;
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    createComment();
    updateComment();
    deleteComment();
  }

  private void createComment() {
    service.post(
        routePrefix,
        (request, response) -> {
          response.type("application/json");

          CommentCreateRequest commentCreateRequest =
              objectMapper.readValue(request.body(), CommentCreateRequest.class);

          long articleId = Long.parseLong(request.params("articleId"));

          long commentId = CommentService.create(
              articleId,
              commentCreateRequest.message()
          );

          LOG.debug(
              "Successfully added new comment with id={} to article with id={}",
              commentId, articleId
          );

          response.status(201);
          return objectMapper.writeValueAsString(new CommentCreateResponse(commentId));
        }
    );
  }

  private void updateComment() {
    service.put(
        routePrefix + "/:commentId",
        (request, response) -> {
          response.type("application/json");

          long articleId = Long.parseLong(request.params("articleId"));
          long commentId = Long.parseLong(request.params("commentId"));
          CommentCreateRequest commentCreateRequest =
              objectMapper.readValue(request.body(), CommentCreateRequest.class);

          try {
            CommentService.update(
                articleId,
                commentId,
                commentCreateRequest.message()
            );

            LOG.debug(
                "Successfully updated comment with id={} in article with id={}",
                commentId, articleId
            );

            response.status(200);
            return objectMapper.writeValueAsString(new CommentCreateResponse(commentId));

          } catch (ArticleNotFoundException e) {
            LOG.warn("Article not found: articleId={}", articleId);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse("Article not found."));

          } catch (CommentNotFoundException e) {
            LOG.warn("Comment not found: articleId={}, commentId={}", articleId, commentId);
            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse("Comment not found."));
          }
        }
    );
  }

  private void deleteComment() {
    service.delete(
        routePrefix + "/:commentId",
        (request, response) -> {
          response.type("application/json");

          long articleId = Long.parseLong(request.params("articleId"));
          long commentId = Long.parseLong(request.params("commentId"));

          CommentService.delete(articleId, commentId);
          LOG.debug("Successfully deleted comment with id={}", commentId);

          response.status(204);
          return "";
        }
    );
  }
}
