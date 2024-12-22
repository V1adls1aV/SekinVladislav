package hw06;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw06.api.route.api.article.ArticleHandler;
import hw06.api.route.api.article.comment.CommentHandler;
import hw06.api.route.page.ArticlePageHandler;
import hw06.db.factory.DatabaseFactory;
import hw06.db.repository.article.ArticleRepository;
import hw06.dto.article.ArticleData;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.CommentData;
import hw06.service.TemplateFactory;
import spark.Service;

import java.util.List;
import java.util.Set;


public class Main {
  private static final String apiPrefix = "/api";

  public static void main(String[] args) {
    DatabaseFactory factory = DatabaseFactory.getInstance();
    ArticleRepository repository = factory.getArticleRepository();

    repository.create(new ArticleData("Abstract Factory Creational Pattern", Set.of("Coding", "Creation Patterns")));
    repository.addComment(new ArticleId(1L), new CommentData("Nice creation pattern! It helped me just now to accomplish the MTS Java homework! I glad to see deep things like that in the studying program!"));
    repository.addComment(new ArticleId(1L), new CommentData("This is a great comment!"));
    repository.create(new ArticleData("The Greatest Title ever.", Set.of()));

    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();

    ArticleHandler articleController = new ArticleHandler(
        apiPrefix + "/article",
        service,
        objectMapper
    );

    CommentHandler commentController = new CommentHandler(
        apiPrefix + "/article/:articleId/comment",
        service,
        objectMapper
    );

    ArticlePageHandler freemarkerController = new ArticlePageHandler(
        "/",
        service,
        TemplateFactory.freeMarkerEngine()
    );

    App app = new App(
        List.of(
            articleController,
            commentController,
            freemarkerController
        )
    );

    app.start();
  }
}
