package assignment.wif3006cbse.features.payment.domain.repository;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, java.util.UUID> {
    List<PaymentTransaction> findByUserId(String userId);

    List<PaymentTransaction> findByProjectId(String projectId);
}
