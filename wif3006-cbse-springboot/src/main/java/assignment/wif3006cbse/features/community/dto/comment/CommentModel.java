package assignment.wif3006cbse.features.community.dto.comment;

public record CommentModel(
    String id,
    String threadId,
    String authorId,
    String content
) {

}
