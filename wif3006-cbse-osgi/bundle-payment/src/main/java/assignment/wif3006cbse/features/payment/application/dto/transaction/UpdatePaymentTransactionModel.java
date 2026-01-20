package assignment.wif3006cbse.features.payment.application.dto.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UpdatePaymentTransactionModel(
        String id,
        String status,
        String invoiceId) {
}
