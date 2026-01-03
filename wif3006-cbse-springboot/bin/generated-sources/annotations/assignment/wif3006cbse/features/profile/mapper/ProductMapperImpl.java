package assignment.wif3006cbse.features.profile.mapper;

import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.features.profile.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.dto.product.UpdateProductModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T17:31:40+0800",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel toModel(Product product) {
        if ( product == null ) {
            return null;
        }

        String id = null;
        String userId = null;
        String name = null;
        String description = null;
        Double price = null;
        String category = null;
        String imageUrl = null;

        if ( product.getId() != null ) {
            id = product.getId();
        }
        if ( product.getUserId() != null ) {
            userId = product.getUserId();
        }
        if ( product.getName() != null ) {
            name = product.getName();
        }
        if ( product.getDescription() != null ) {
            description = product.getDescription();
        }
        if ( product.getPrice() != null ) {
            price = product.getPrice();
        }
        if ( product.getCategory() != null ) {
            category = product.getCategory();
        }
        if ( product.getImageUrl() != null ) {
            imageUrl = product.getImageUrl();
        }

        ProductModel productModel = new ProductModel( id, userId, name, description, price, category, imageUrl );

        return productModel;
    }

    @Override
    public Product toEntity(CreateProductModel createProductModel) {
        if ( createProductModel == null ) {
            return null;
        }

        Product product = new Product();

        if ( createProductModel.category() != null ) {
            product.setCategory( createProductModel.category() );
        }
        if ( createProductModel.description() != null ) {
            product.setDescription( createProductModel.description() );
        }
        if ( createProductModel.imageUrl() != null ) {
            product.setImageUrl( createProductModel.imageUrl() );
        }
        if ( createProductModel.name() != null ) {
            product.setName( createProductModel.name() );
        }
        if ( createProductModel.price() != null ) {
            product.setPrice( createProductModel.price() );
        }
        if ( createProductModel.userId() != null ) {
            product.setUserId( createProductModel.userId() );
        }

        return product;
    }

    @Override
    public void updateEntityFromUpdateModel(Product product, UpdateProductModel updateProductModel) {
        if ( updateProductModel == null ) {
            return;
        }

        if ( updateProductModel.category() != null ) {
            product.setCategory( updateProductModel.category() );
        }
        if ( updateProductModel.description() != null ) {
            product.setDescription( updateProductModel.description() );
        }
        if ( updateProductModel.imageUrl() != null ) {
            product.setImageUrl( updateProductModel.imageUrl() );
        }
        if ( updateProductModel.name() != null ) {
            product.setName( updateProductModel.name() );
        }
        if ( updateProductModel.price() != null ) {
            product.setPrice( updateProductModel.price() );
        }
    }
}
