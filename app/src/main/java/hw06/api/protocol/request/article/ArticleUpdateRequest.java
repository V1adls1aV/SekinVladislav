package hw06.api.protocol.request.article;

import java.util.Set;

public record ArticleUpdateRequest(String title, Set<String> tags) {
}