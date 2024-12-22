package hw06.core.exceptions.service;

public class ArticleNotFoundException extends RuntimeException {
  public ArticleNotFoundException() {
    super("Article with such id not found.");
  }
}
