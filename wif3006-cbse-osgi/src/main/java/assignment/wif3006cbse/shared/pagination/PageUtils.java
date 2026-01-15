package assignment.wif3006cbse.shared.pagination;

import java.util.List;

public final class PageUtils {

    private PageUtils() {
    }

    public static <T> PageModel<T> toPage(List<T> source, int page, int size) {
        int totalSize = source.size();
        int totalPages = size == 0 ? 0 : (int) Math.ceil((double) totalSize / size);

        if (size == 0 || page < 0 || page >= totalPages) {
            return new PageModel<>(
                java.util.Collections.emptyList(),
                page,
                size,
                totalPages,
                totalSize
            );
        }

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalSize);

        return new PageModel<>(
            source.subList(fromIndex, toIndex),
            page,
            size,
            totalPages,
            totalSize
        );
    }
}
