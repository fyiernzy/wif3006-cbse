package assignment.wif3006cbse.features.payment.service;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentMethodRepository;
import assignment.wif3006cbse.features.payment.dto.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.mapper.PaymentMethodMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethodModel createPaymentMethod(@Valid CreatePaymentMethodModel createPaymentMethodModel) {
        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(createPaymentMethodModel);
        return paymentMethodMapper.toModel(paymentMethodRepository.save(paymentMethod));
    }

    @Transactional(readOnly = true)
    public PaymentMethodModel findPaymentMethodById(@NotNull java.util.UUID id) {
        return paymentMethodMapper.toModel(paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found.")));
    }

    @Transactional
    public PaymentMethodModel updatePaymentMethod(@Valid UpdatePaymentMethodModel updatePaymentMethodModel) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(updatePaymentMethodModel.id())
                .orElseThrow(() -> new EntityNotFoundException("PaymentMethod not found."));
        paymentMethodMapper.updateEntityFromUpdateModel(paymentMethod, updatePaymentMethodModel);
        return paymentMethodMapper.toModel(paymentMethodRepository.save(paymentMethod));
    }

    @Transactional
    public ResponseEntity<Void> deletePaymentMethodById(@NotNull java.util.UUID id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new EntityNotFoundException("PaymentMethod not found.");
        }
        paymentMethodRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodModel> findAllPaymentMethodsByUserId(@NotNull String userId) {
        return paymentMethodRepository.findByUserId(userId).stream()
                .map(paymentMethodMapper::toModel)
                .collect(Collectors.toList());
    }
}
