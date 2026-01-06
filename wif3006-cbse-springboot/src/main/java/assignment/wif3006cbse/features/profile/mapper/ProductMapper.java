package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.features.profile.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.dto.product.UpdateProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface ProductMapper {

    ProductModel toModel(Product product);

    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(CreateProductModel createProductModel);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget Product product, UpdateProductModel updateProductModel);
}
