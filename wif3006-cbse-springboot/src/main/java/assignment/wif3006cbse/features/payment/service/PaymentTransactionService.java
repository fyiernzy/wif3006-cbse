package assignment.wif3006cbse.features.payment.service;

import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentTransactionRepository;
import assignment.wif3006cbse.features.payment.dto.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.mapper.PaymentTransactionMapper;
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
public class PaymentTransactionService {

    private final PaymentTransactionMapper paymentTransactionMapper;
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional
    public PaymentTransactionModel createPaymentTransaction(
            @Valid CreatePaymentTransactionModel createPaymentTransactionModel) {
        PaymentTransaction paymentTransaction = paymentTransactionMapper.toEntity(createPaymentTransactionModel);
        return paymentTransactionMapper.toModel(paymentTransactionRepository.save(paymentTransaction));
    }

    @Transactional(readOnly = true)
    public PaymentTransactionModel findPaymentTransactionById(@NotNull java.util.UUID id) {
        return paymentTransactionMapper.toModel(paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PaymentTransaction not found.")));
    }

    @Transactional
    public PaymentTransactionModel updatePaymentTransaction(
            @Valid UpdatePaymentTransactionModel updatePaymentTransactionModel) {
        PaymentTransaction paymentTransaction = paymentTransactionRepository
                .findById(updatePaymentTransactionModel.id())
                .orElseThrow(() -> new EntityNotFoundException("PaymentTransaction not found."));
        paymentTransactionMapper.updateEntityFromUpdateModel(paymentTransaction, updatePaymentTransactionModel);
        return paymentTransactionMapper.toModel(paymentTransactionRepository.save(paymentTransaction));
    }

    @Transactional
    public ResponseEntity<Void> deletePaymentTransactionById(@NotNull java.util.UUID id) {
        if (!paymentTransactionRepository.existsById(id)) {
            throw new EntityNotFoundException("PaymentTransaction not found.");
        }
        paymentTransactionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional(readOnly = true)
    public List<PaymentTransactionModel> findPaymentTransactionsByUserId(@NotNull String userId) {
        return paymentTransactionRepository.findByUserId(userId).stream()
                .map(paymentTransactionMapper::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PaymentTransactionModel> findPaymentTransactionsByProjectId(@NotNull String projectId) {
        return paymentTransactionRepository.findByProjectId(projectId).stream()
                .map(paymentTransactionMapper::toModel)
                .collect(Collectors.toList());
    }

    public String generateInvoice(@NotNull java.util.UUID transactionId) {
        PaymentTransaction transaction = paymentTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("PaymentTransaction not found."));

        // Simple text invoice generation
        return String.format(
                "INVOICE\n=======\nTransaction ID: %s\nDate: %s\nAmount: %s\nStatus: %s\nPayer ID: %s\nProject ID: %s",
                transaction.getId(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getUserId(),
                transaction.getProjectId());
    }
}
