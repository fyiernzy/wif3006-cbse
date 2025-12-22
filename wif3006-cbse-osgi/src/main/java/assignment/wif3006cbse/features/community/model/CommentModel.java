package assignment.wif3006cbse.features.community.model;

public record CommentModel(
        String id,
        String threadId,
        String authorId,
        String content) {
}
