package hw06.core.exceptions.service;

public class ArticleUpdateException extends RuntimeException {
  public ArticleUpdateException() {
    super("The article to update is not found.");
  }
}
