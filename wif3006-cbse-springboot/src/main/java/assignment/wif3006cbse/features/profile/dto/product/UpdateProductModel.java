package assignment.wif3006cbse.features.profile.dto.product;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductModel(
    @NotBlank String id,
    String name,
    String description,
    Double price,
    String category,
    String imageUrl
) {

}
