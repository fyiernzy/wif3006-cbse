package assignment.wif3006cbse.features.profile.service;

import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.features.profile.domain.repository.ProductRepository;
import assignment.wif3006cbse.features.profile.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.dto.product.UpdateProductModel;
import assignment.wif3006cbse.features.profile.mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Transactional
    public ProductModel createProduct(@Valid CreateProductModel createProductModel) {
        Product product = productMapper.toEntity(createProductModel);
        return productMapper.toModel(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public ProductModel findProductById(@NotBlank String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found."));
        return productMapper.toModel(product);
    }

    @Transactional(readOnly = true)
    public List<ProductModel> findAllByUserId(@NotBlank String userId) {
        return productRepository.findAllByUserId(userId)
            .stream()
            .map(productMapper::toModel)
            .toList();
    }

    @Transactional(readOnly = true)
    public Page<ProductModel> findAllByUserId(@NotBlank String userId, Pageable pageable) {
        return productRepository.findAllByUserId(userId, pageable)
            .map(productMapper::toModel);
    }

    @Transactional(readOnly = true)
    public List<ProductModel> findAllByCategory(@NotBlank String category) {
        return productRepository.findAllByCategory(category)
            .stream()
            .map(productMapper::toModel)
            .toList();
    }

    @Transactional
    public ProductModel updateProduct(@Valid UpdateProductModel updateProductModel) {
        Product product = productRepository.findById(updateProductModel.id())
            .orElseThrow(() -> new EntityNotFoundException("Product not found."));
        productMapper.updateEntityFromUpdateModel(product, updateProductModel);
        return productMapper.toModel(productRepository.save(product));
    }

    @Transactional
    public void deleteProductById(@NotBlank String id) {
        productRepository.deleteById(id);
    }
}
