package assignment.wif3006cbse.features.community.model;

import java.io.Serializable;
import java.util.UUID;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String threadId;
    private String authorId;
    private String content;

    public Comment() {
        this.id = UUID.randomUUID().toString();
    }

    public Comment(String threadId, String authorId, String content) {
        this.id = UUID.randomUUID().toString();
        this.threadId = threadId;
        this.authorId = authorId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
