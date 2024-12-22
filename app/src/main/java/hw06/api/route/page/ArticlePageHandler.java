package hw06.api.route.page;

import hw06.api.route.Handler;
import hw06.dto.article.Article;
import hw06.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class ArticlePageHandler implements Handler {
  private static final Logger LOG = LoggerFactory.getLogger(ArticlePageHandler.class);

  private final String route;
  private final Service service;
  private final FreeMarkerEngine freeMarkerEngine;

  public ArticlePageHandler(String route, Service service, FreeMarkerEngine freeMarkerEngine) {
    this.route = route;
    this.service = service;
    this.freeMarkerEngine = freeMarkerEngine;
  }

  @Override
  public void initializeEndpoints() {
    getArticleTable();
  }

  private void getArticleTable() {
    service.get(
        route,
        (Request request, Response response) -> {
          response.type("text/html; charset=utf-8");

          List<Article> articles = ArticleService.getAll();
          List<Map<String, String>> articleMapList =
              articles.stream()
                  .map(article ->
                      Map.of(
                          "id", article.id().toString(),
                          "title", article.title(),
                          "tags", String.join(" ", article.tags()),
                          "comment_count",
                          String.valueOf(article.comments().size())
                      )
                  )
                  .toList();

          LOG.debug("Successfully rendered article table");

          Map<String, Object> model = new HashMap<>();
          model.put("articles", articleMapList);
          return freeMarkerEngine.render(new ModelAndView(model, "index.ftl"));
        }
    );
  }
}
