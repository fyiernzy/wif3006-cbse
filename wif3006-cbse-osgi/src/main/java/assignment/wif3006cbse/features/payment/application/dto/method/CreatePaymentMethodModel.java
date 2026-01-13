package assignment.wif3006cbse.features.payment.application.dto.method;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreatePaymentMethodModel(
        String userId,
        String type,
        String provider,
        String accountNumber,
        String expiryDate,
        Boolean isDefault) {
}
