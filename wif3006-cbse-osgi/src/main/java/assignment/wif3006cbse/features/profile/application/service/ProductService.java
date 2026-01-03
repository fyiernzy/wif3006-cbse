package assignment.wif3006cbse.features.profile.application.service;

import assignment.wif3006cbse.features.profile.application.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.UpdateProductModel;

import java.util.List;

public interface ProductService {

    ProductModel createProduct(CreateProductModel createProductModel);

    ProductModel findProductById(String id);

    List<ProductModel> findAllByUserId(String userId);

    List<ProductModel> findAllByCategory(String category);

    ProductModel updateProduct(UpdateProductModel updateProductModel);

    void deleteProduct(String id);
}
