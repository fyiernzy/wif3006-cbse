package assignment.wif3006cbse.features.payment.domain.repository;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.List;

public interface PaymentTransactionRepository extends CrudRepository<PaymentTransaction, String> {
    List<PaymentTransaction> findByUserId(String userId);

    List<PaymentTransaction> findByProjectId(String projectId);
}
