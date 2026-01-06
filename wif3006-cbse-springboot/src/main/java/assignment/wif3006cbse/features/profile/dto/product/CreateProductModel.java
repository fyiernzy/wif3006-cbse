package assignment.wif3006cbse.features.profile.dto.product;

import jakarta.validation.constraints.NotBlank;

public record CreateProductModel(
    @NotBlank String userId,
    @NotBlank String name,
    String description,
    Double price,
    String category,
    String imageUrl
) {

}
