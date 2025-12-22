package assignment.wif3006cbse.features.community.model;

public record CreateCommentModel(
        String threadId,
        String authorId,
        String content) {
}
