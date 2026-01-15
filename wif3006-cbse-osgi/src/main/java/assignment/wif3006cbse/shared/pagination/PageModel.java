package assignment.wif3006cbse.shared.pagination;

import java.util.List;

public final class PageModel<T> {

    private final List<T> content;
    private final int page;
    private final int size;
    private final int totalPages;
    private final long totalSize;

    public PageModel(List<T> content, int page, int size, int totalPages, long totalSize) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalSize = totalSize;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalSize() {
        return totalSize;
    }
}
