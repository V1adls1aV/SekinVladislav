package hw06.dto.article;

import java.util.Set;

/**
 * Represent an article data. Used to creating new articles.
 */
public record ArticleData(String title, Set<String> tags) {
}
