package assignment.wif3006cbse.features.payment.domain.repository.impl;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentTransactionRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PaymentTransactionRepository.class)
public class PaymentTransactionRepositoryImpl extends FileBasedRepository<PaymentTransaction, String>
        implements PaymentTransactionRepository {

    public PaymentTransactionRepositoryImpl() {
        super("payment_transactions.dat", PaymentTransaction::getId);
    }

    @Override
    public List<PaymentTransaction> findByUserId(String userId) {
        return getStore().values().stream()
                .filter(pt -> pt.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentTransaction> findByProjectId(String projectId) {
        return getStore().values().stream()
                .filter(pt -> pt.getProjectId().equals(projectId))
                .collect(Collectors.toList());
    }
}
