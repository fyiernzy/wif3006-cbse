package assignment.wif3006cbse.features.payment.application.dto.method;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record PaymentMethodModel(
        String id,
        String userId,
        String type,
        String provider,
        String accountNumber,
        String expiryDate,
        Boolean isDefault,
        LocalDateTime createdAt) {
}
