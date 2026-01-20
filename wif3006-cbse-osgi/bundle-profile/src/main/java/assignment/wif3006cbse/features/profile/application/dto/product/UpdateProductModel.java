package assignment.wif3006cbse.features.profile.application.dto.product;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdateProductModel(
    String id,
    String title,
    String description,
    String category,
    double price,
    String imageUrl
) {

}
