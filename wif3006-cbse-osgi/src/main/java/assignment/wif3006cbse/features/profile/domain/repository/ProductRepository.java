package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.Product;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findAllByUserId(String userId);

    List<Product> findAllByCategory(String category);
}
