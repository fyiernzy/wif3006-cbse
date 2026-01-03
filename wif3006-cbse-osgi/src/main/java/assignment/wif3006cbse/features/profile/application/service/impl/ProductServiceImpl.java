package assignment.wif3006cbse.features.profile.application.service.impl;

import assignment.wif3006cbse.features.profile.application.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.UpdateProductModel;
import assignment.wif3006cbse.features.profile.application.service.ProductService;
import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.features.profile.domain.repository.ProductRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ProductService.class)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Activate
    public ProductServiceImpl(@Reference ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductServiceImpl() {
        this.productRepository = null;
    }

    @Override
    public ProductModel createProduct(CreateProductModel createProductModel) {
        Product product = new Product(
            createProductModel.userId(),
            createProductModel.title(),
            createProductModel.description(),
            createProductModel.price()
        );
        product.setCategory(createProductModel.category());
        product.setImageUrl(createProductModel.imageUrl());

        Product saved = productRepository.save(product);
        return toModel(saved);
    }

    @Override
    public ProductModel findProductById(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        return toModel(product);
    }

    @Override
    public List<ProductModel> findAllByUserId(String userId) {
        return productRepository.findAllByUserId(userId).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductModel> findAllByCategory(String category) {
        return productRepository.findAllByCategory(category).stream()
            .map(this::toModel)
            .collect(Collectors.toList());
    }

    @Override
    public ProductModel updateProduct(UpdateProductModel updateProductModel) {
        Product product = productRepository.findById(updateProductModel.id())
            .orElseThrow(() -> new IllegalArgumentException(
                "Product not found: " + updateProductModel.id()));

        if (updateProductModel.title() != null) {
            product.setTitle(updateProductModel.title());
        }
        if (updateProductModel.description() != null) {
            product.setDescription(updateProductModel.description());
        }
        if (updateProductModel.category() != null) {
            product.setCategory(updateProductModel.category());
        }
        if (updateProductModel.price() > 0) {
            product.setPrice(updateProductModel.price());
        }
        if (updateProductModel.imageUrl() != null) {
            product.setImageUrl(updateProductModel.imageUrl());
        }

        Product saved = productRepository.save(product);
        return toModel(saved);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    private ProductModel toModel(Product product) {
        return new ProductModel(
            product.getId(),
            product.getUserId(),
            product.getTitle(),
            product.getDescription(),
            product.getCategory(),
            product.getPrice(),
            product.getImageUrl(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
}
