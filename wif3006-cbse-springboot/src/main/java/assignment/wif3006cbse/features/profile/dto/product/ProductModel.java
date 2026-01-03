package assignment.wif3006cbse.features.profile.dto.product;

public record ProductModel(
    String id,
    String userId,
    String name,
    String description,
    Double price,
    String category,
    String imageUrl
) {

}
