package hw06.dto.article;

import hw06.dto.Id;

/**
 * @param id an article identifier.
 */
public record ArticleId (long id) implements Id {}
