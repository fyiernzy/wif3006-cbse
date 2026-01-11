package assignment.wif3006cbse.features.payment.application.service.impl;

import assignment.wif3006cbse.features.payment.application.dto.transaction.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.UpdatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.service.PaymentTransactionService;
import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import assignment.wif3006cbse.features.payment.domain.repository.PaymentTransactionRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = PaymentTransactionService.class)
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepository paymentTransactionRepository;

    @Activate
    public PaymentTransactionServiceImpl(@Reference PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    // Fallback no-args
    public PaymentTransactionServiceImpl() {
        this.paymentTransactionRepository = null;
    }

    @Override
    public PaymentTransactionModel createPaymentTransaction(CreatePaymentTransactionModel createModel) {
        PaymentTransaction transaction = new PaymentTransaction(
                createModel.userId(),
                createModel.projectId(),
                createModel.paymentMethodId(),
                createModel.amount(),
                "PENDING" // Default status
        );

        PaymentTransaction saved = paymentTransactionRepository.save(transaction);
        return toModel(saved);
    }

    @Override
    public PaymentTransactionModel findPaymentTransactionById(String id) {
        PaymentTransaction transaction = paymentTransactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("PaymentTransaction not found: " + id));
        return toModel(transaction);
    }

    @Override
    public PaymentTransactionModel updatePaymentTransaction(UpdatePaymentTransactionModel updateModel) {
        PaymentTransaction transaction = paymentTransactionRepository.findById(updateModel.id())
                .orElseThrow(() -> new IllegalArgumentException("PaymentTransaction not found: " + updateModel.id()));

        if (updateModel.status() != null) {
            transaction.setStatus(updateModel.status());
        }
        if (updateModel.invoiceId() != null) {
            transaction.setInvoiceId(updateModel.invoiceId());
        }

        PaymentTransaction saved = paymentTransactionRepository.save(transaction);
        return toModel(saved);
    }

    @Override
    public void deletePaymentTransactionById(String id) {
        if (!paymentTransactionRepository.existsById(id)) {
            throw new IllegalArgumentException("PaymentTransaction not found: " + id);
        }
        paymentTransactionRepository.deleteById(id);
    }

    @Override
    public List<PaymentTransactionModel> findPaymentTransactionsByUserId(String userId) {
        return paymentTransactionRepository.findByUserId(userId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentTransactionModel> findPaymentTransactionsByProjectId(String projectId) {
        return paymentTransactionRepository.findByProjectId(projectId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public String generateInvoice(String transactionId) {
        PaymentTransaction transaction = paymentTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("PaymentTransaction not found: " + transactionId));

        return String.format(
                "INVOICE\n=======\nTransaction ID: %s\nDate: %s\nAmount: %s\nStatus: %s\nPayer ID: %s\nProject ID: %s",
                transaction.getId(),
                transaction.getTransactionDate(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getUserId(),
                transaction.getProjectId());
    }

    private PaymentTransactionModel toModel(PaymentTransaction entity) {
        return new PaymentTransactionModel(
                entity.getId(),
                entity.getUserId(),
                entity.getProjectId(),
                entity.getPaymentMethodId(),
                entity.getAmount(),
                entity.getTransactionDate(),
                entity.getStatus(),
                entity.getInvoiceId());
    }
}
