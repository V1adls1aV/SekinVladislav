package hw06.dto.comment;

import hw06.dto.Id;

/**
 * @param id a comment identifier
 */
public record CommentId (long id) implements Id {}
