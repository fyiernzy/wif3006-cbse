package assignment.wif3006cbse.features.community.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class ThreadEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String content;
    private String postId;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ThreadEntity(String content, String authorId, String postId) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
