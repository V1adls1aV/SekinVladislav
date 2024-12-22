package hw06.api.route.api.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw06.api.protocol.request.article.ArticleCreateRequest;
import hw06.api.protocol.request.article.ArticleUpdateRequest;
import hw06.api.protocol.response.ErrorResponse;
import hw06.api.protocol.response.article.ArticleCreateResponse;
import hw06.api.protocol.response.article.ArticleGetResponse;
import hw06.api.route.Handler;
import hw06.core.exceptions.service.ArticleUpdateException;
import hw06.dto.article.Article;
import hw06.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;

import java.util.Optional;


public final class ArticleHandler implements Handler {
  private final String routePrefix;
  private final Service service;
  private final ObjectMapper objectMapper;

  private static final Logger LOG = LoggerFactory.getLogger(ArticleHandler.class);

  public ArticleHandler(
      String routePrefix,
      Service service, ObjectMapper objectMapper
  ) {
    this.routePrefix = routePrefix;
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @Override
  public void initializeEndpoints() {
    getAllArticles();
    getArticle();
    createArticle();
    updateArticle();
    deleteArticle();
  }

  private void getAllArticles() {
    service.get(
        routePrefix + "/list",
        (request, response) -> {
          response.type("application/json");

          LOG.debug("Successfully found all articles");
          response.status(200);

          return objectMapper.writeValueAsString(ArticleService.getAll());
        }
    );
  }

  private void getArticle() {
    service.get(
        routePrefix + "/:id",
        (request, response) -> {
          response.type("application/json");

          long articleId = Long.parseLong(request.params("id"));
          Optional<Article> article = ArticleService.getById(articleId);

          if (article.isPresent()) {
            LOG.debug("Successfully found article by id={}", articleId);

            response.status(200);
            return objectMapper.writeValueAsString(new ArticleGetResponse(article.get()));

          } else {
            LOG.info("Article with id={} not found.", articleId);

            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(String.format("Article with id=%d not found", articleId)));
          }
        }
    );
  }

  private void createArticle() {
    service.post(
        routePrefix,
        (request, response) -> {
          response.type("application/json");

          ArticleCreateRequest articleCreateRequest =
              objectMapper.readValue(request.body(), ArticleCreateRequest.class);
          long articleId = ArticleService.create(
              articleCreateRequest.title(),
              articleCreateRequest.tags()
          );

          LOG.debug("Successfully created article with id={}", articleId);

          response.status(201);
          return objectMapper.writeValueAsString(new ArticleCreateResponse(articleId));
        }
    );
  }

  private void updateArticle() {
    service.put(
        routePrefix + "/:id",
        (request, response) -> {
          response.type("application/json");

          long articleId = Long.parseLong(request.params("id"));
          ArticleUpdateRequest articleUpdateRequest =
              objectMapper.readValue(request.body(), ArticleUpdateRequest.class);

          try {
            ArticleService.update(
                articleId,
                articleUpdateRequest.title(),
                articleUpdateRequest.tags()
            );

            LOG.debug("Successfully updated article by id={}", articleId);

            response.status(204);
            return "";
          } catch (ArticleUpdateException e) {
            LOG.warn("Error updating article by id={}", articleId, e);

            response.status(404);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void deleteArticle() {
    service.delete(
        routePrefix + "/:id",
        (request, response) -> {
          response.type("application/json");

          long articleId = Long.parseLong(request.params("id"));
          ArticleService.delete(articleId);

          LOG.debug("Successfully deleted article by id={}", articleId);
          response.status(204);
          return "";
        }
    );
  }
}
