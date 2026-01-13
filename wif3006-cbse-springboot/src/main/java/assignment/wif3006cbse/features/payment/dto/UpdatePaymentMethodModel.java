package assignment.wif3006cbse.features.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePaymentMethodModel(
        @NotNull java.util.UUID id,
        @NotNull String userId,
        @NotBlank String type,
        @NotBlank String provider,
        @NotBlank String accountNumber,
        String expiryDate,
        Boolean isDefault) {
}
