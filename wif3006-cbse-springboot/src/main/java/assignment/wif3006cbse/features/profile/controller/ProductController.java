package assignment.wif3006cbse.features.profile.controller;

import assignment.wif3006cbse.features.profile.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.dto.product.UpdateProductModel;
import assignment.wif3006cbse.features.profile.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductModel createProduct(@RequestBody @Valid CreateProductModel createProductModel) {
        return productService.createProduct(createProductModel);
    }

    @GetMapping("/{id}")
    public ProductModel findProductById(@PathVariable @NotBlank String id) {
        return productService.findProductById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ProductModel> findAllByUserId(@PathVariable @NotBlank String userId) {
        return productService.findAllByUserId(userId);
    }

    @GetMapping("/user/{userId}/paged")
    public PagedModel<ProductModel> findAllByUserIdPaged(@PathVariable @NotBlank String userId,
                                                         @PageableDefault Pageable pageable) {
        return new PagedModel<>(productService.findAllByUserId(userId, pageable));
    }

    @GetMapping("/category/{category}")
    public List<ProductModel> findAllByCategory(@PathVariable @NotBlank String category) {
        return productService.findAllByCategory(category);
    }

    @PutMapping
    public ProductModel updateProduct(@RequestBody @Valid UpdateProductModel updateProductModel) {
        return productService.updateProduct(updateProductModel);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable @NotBlank String id) {
        productService.deleteProductById(id);
    }
}
