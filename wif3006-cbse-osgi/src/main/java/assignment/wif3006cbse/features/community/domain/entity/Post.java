package assignment.wif3006cbse.features.community.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String authorId;
    private String title;
    private String content;
    private java.time.LocalDateTime createdAt;

    public Post() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = java.time.LocalDateTime.now();
    }

    public Post(String authorId, String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.createdAt = java.time.LocalDateTime.now();
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
