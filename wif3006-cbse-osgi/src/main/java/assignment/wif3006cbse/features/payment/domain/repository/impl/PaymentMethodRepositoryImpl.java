package assignment.wif3006cbse.features.payment.domain.repository.impl;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentMethodRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PaymentMethodRepository.class)
public class PaymentMethodRepositoryImpl extends FileBasedRepository<PaymentMethod, String>
        implements PaymentMethodRepository {

    public PaymentMethodRepositoryImpl() {
        super("payment_methods.dat", PaymentMethod::getId);
    }

    @Override
    public List<PaymentMethod> findByUserId(String userId) {
        return getStore().values().stream()
                .filter(pm -> pm.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
