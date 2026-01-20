package assignment.wif3006cbse.shared.pagination;

import java.util.List;

public record Page<T>(
    List<T> content,
    int page,
    int size,
    int totalPages,
    long totalSize
) {

}
