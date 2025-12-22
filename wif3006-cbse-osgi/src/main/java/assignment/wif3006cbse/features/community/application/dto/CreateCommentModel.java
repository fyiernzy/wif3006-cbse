package assignment.wif3006cbse.features.community.application.dto;

public record CreateCommentModel(
    String threadId,
    String authorId,
    String content
) {

}
