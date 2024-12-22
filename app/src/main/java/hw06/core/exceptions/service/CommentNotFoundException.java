package hw06.core.exceptions.service;

public class CommentNotFoundException extends RuntimeException {
  public CommentNotFoundException() {
    super("Comment with such id not found.");
  }
}
