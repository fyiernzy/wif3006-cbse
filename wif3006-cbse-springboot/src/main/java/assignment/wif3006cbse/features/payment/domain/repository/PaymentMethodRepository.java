package assignment.wif3006cbse.features.payment.domain.repository;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, java.util.UUID> {
    List<PaymentMethod> findByUserId(String userId);
}
