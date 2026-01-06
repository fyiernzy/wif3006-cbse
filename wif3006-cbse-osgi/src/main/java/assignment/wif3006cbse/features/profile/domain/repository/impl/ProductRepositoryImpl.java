package assignment.wif3006cbse.features.profile.domain.repository.impl;

import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.features.profile.domain.repository.ProductRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = ProductRepository.class)
public class ProductRepositoryImpl extends FileBasedRepository<Product, String> implements
    ProductRepository {

    public ProductRepositoryImpl() {
        super("products.dat", Product::getId);
    }

    @Override
    public List<Product> findAllByUserId(String userId) {
        return getStore().values().stream()
            .filter(p -> p.getUserId() != null && p.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        return getStore().values().stream()
            .filter(p -> p.getCategory() != null && p.getCategory().equals(category))
            .collect(Collectors.toList());
    }
}
