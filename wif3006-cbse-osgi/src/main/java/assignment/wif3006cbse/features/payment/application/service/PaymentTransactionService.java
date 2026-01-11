package assignment.wif3006cbse.features.payment.application.service;

import assignment.wif3006cbse.features.payment.application.dto.transaction.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.UpdatePaymentTransactionModel;

import java.util.List;

public interface PaymentTransactionService {
    PaymentTransactionModel createPaymentTransaction(CreatePaymentTransactionModel createPaymentTransactionModel);

    PaymentTransactionModel findPaymentTransactionById(String id);

    PaymentTransactionModel updatePaymentTransaction(UpdatePaymentTransactionModel updatePaymentTransactionModel);

    void deletePaymentTransactionById(String id);

    List<PaymentTransactionModel> findPaymentTransactionsByUserId(String userId);

    List<PaymentTransactionModel> findPaymentTransactionsByProjectId(String projectId);

    String generateInvoice(String transactionId);
}
