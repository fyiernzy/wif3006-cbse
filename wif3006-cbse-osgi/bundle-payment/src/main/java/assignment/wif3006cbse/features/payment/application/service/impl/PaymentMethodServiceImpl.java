package assignment.wif3006cbse.features.payment.application.service.impl;

import assignment.wif3006cbse.features.payment.application.dto.method.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.UpdatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.service.PaymentMethodService;
import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentMethodRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PaymentMethodService.class)
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Activate
    public PaymentMethodServiceImpl(@Reference PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    // Fallback no-args (DS compliant)
    public PaymentMethodServiceImpl() {
        this.paymentMethodRepository = null;
    }

    @Override
    public PaymentMethodModel createPaymentMethod(CreatePaymentMethodModel createModel) {
        PaymentMethod paymentMethod = new PaymentMethod(
                createModel.userId(),
                createModel.type(),
                createModel.provider(),
                createModel.accountNumber(),
                createModel.expiryDate(),
                createModel.isDefault());
        PaymentMethod saved = paymentMethodRepository.save(paymentMethod);
        return toModel(saved);
    }

    @Override
    public PaymentMethodModel findPaymentMethodById(String id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PaymentMethod not found: " + id));
        return toModel(paymentMethod);
    }

    @Override
    public PaymentMethodModel updatePaymentMethod(UpdatePaymentMethodModel updateModel) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(updateModel.id())
                .orElseThrow(() -> new IllegalArgumentException("PaymentMethod not found: " + updateModel.id()));

        paymentMethod.setType(updateModel.type());
        paymentMethod.setProvider(updateModel.provider());
        paymentMethod.setAccountNumber(updateModel.accountNumber());
        paymentMethod.setExpiryDate(updateModel.expiryDate());
        paymentMethod.setIsDefault(updateModel.isDefault());

        PaymentMethod saved = paymentMethodRepository.save(paymentMethod);
        return toModel(saved);
    }

    @Override
    public void deletePaymentMethodById(String id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new IllegalArgumentException("PaymentMethod not found: " + id);
        }
        paymentMethodRepository.deleteById(id);
    }

    @Override
    public List<PaymentMethodModel> findAllPaymentMethodsByUserId(String userId) {
        return paymentMethodRepository.findByUserId(userId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private PaymentMethodModel toModel(PaymentMethod entity) {
        return new PaymentMethodModel(
                entity.getId(),
                entity.getUserId(),
                entity.getType(),
                entity.getProvider(),
                entity.getAccountNumber(),
                entity.getExpiryDate(),
                entity.getIsDefault(),
                entity.getCreatedAt());
    }
}
