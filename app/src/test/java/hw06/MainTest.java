package hw06;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw06.api.protocol.response.article.ArticleCreateResponse;
import hw06.api.protocol.response.article.ArticleGetResponse;
import hw06.api.protocol.response.comment.CommentCreateResponse;
import hw06.api.route.api.article.ArticleHandler;
import hw06.api.route.api.article.comment.CommentHandler;
import hw06.api.route.page.ArticlePageHandler;
import hw06.dto.article.ArticleId;
import hw06.dto.comment.CommentId;
import hw06.service.TemplateFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
  private static final String apiPrefix = "/api";
  private Service service;

  @BeforeEach
  void befofeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void shouldExecuteAllRequestsCorrectly() throws Exception {
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
    service.awaitInitialization();

    HttpResponse<String> responseOfCreatingArticle = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .POST(
                    BodyPublishers.ofString(
                        """
                            {
                              "title": "Abstract Factory",
                              "tags": ["Coding Patterns", "Creation Patterns"]
                            }
                            """
                    )
                )
                .uri(URI.create("http://localhost:%d/api/article".formatted(service.port())))
                .build(),
            BodyHandlers.ofString(UTF_8)
        );

    assertEquals(201, responseOfCreatingArticle.statusCode());
    ArticleCreateResponse articleCreateResponse =
        objectMapper.readValue(responseOfCreatingArticle.body(), ArticleCreateResponse.class);
    assertEquals(new ArticleId(1L), new ArticleId(articleCreateResponse.id()));

    HttpResponse<String> responseOfCreatingComment = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .POST(
                    BodyPublishers.ofString(
                        """
                            {
                                "message": "Nice creation pattern! It helped me just now to accomplish the MTS Java homework! I glad to see deep things like that in the studying program!"
                            }
                            """
                    )
                )
                .uri(URI.create("http://localhost:%d/api/article/1/comment".formatted(service.port())))
                .build(),
            BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, responseOfCreatingComment.statusCode());
    CommentCreateResponse commentCreateResponse =
        objectMapper.readValue(responseOfCreatingComment.body(), CommentCreateResponse.class);
    assertEquals(new CommentId(1L), new CommentId(commentCreateResponse.id()));

    HttpResponse<String> responseForUpdateArticle = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .PUT(
                    BodyPublishers.ofString(
                        """
                            {
                              "title": "Abstract Factory Pattern",
                              "tags": ["Coding", "Coding Patterns", "Creation Coding Patterns"]
                            }
                            """
                    )
                )
                .uri(URI.create("http://localhost:%d/api/article/1".formatted(service.port())))
                .build(),
            BodyHandlers.ofString(UTF_8)
        );

    assertEquals(204, responseForUpdateArticle.statusCode());

    HttpResponse<String> responseOfDeleteComment = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:%d/api/article/1/comment/1".formatted(service.port())))
                .build(),
            BodyHandlers.ofString(UTF_8)
        );

    assertEquals(204, responseOfDeleteComment.statusCode());

    HttpResponse<String> responseOfGetArticle = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:%d/api/article/1".formatted(service.port())))
                .build(),
            BodyHandlers.ofString(UTF_8)
        );
    assertEquals(200, responseOfGetArticle.statusCode());

    ArticleGetResponse articleGetResponse = objectMapper.readValue(responseOfGetArticle.body(), ArticleGetResponse.class);
    assertEquals(articleGetResponse.id(), 1L);
    assertEquals("Abstract Factory Pattern", articleGetResponse.title());
    assertEquals(Set.of("Coding", "Coding Patterns", "Creation Coding Patterns"), new HashSet<>(articleGetResponse.tags()));
    assertEquals(List.of(), articleGetResponse.comments());
  }
}