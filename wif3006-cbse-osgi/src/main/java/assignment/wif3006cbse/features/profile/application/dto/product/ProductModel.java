package assignment.wif3006cbse.features.profile.application.dto.product;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ProductModel(
    String id,
    String userId,
    String title,
    String description,
    String category,
    double price,
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
