package hw06.api.protocol.request.article;

import java.util.Set;

public record ArticleCreateRequest(String title, Set<String> tags) {
}