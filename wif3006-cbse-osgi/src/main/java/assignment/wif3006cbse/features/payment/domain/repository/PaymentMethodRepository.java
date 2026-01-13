package assignment.wif3006cbse.features.payment.domain.repository;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, String> {
    List<PaymentMethod> findByUserId(String userId);
}
