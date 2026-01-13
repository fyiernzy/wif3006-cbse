package assignment.wif3006cbse.features.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePaymentMethodModel(
                @NotNull String userId,
                @NotBlank String type,
                @NotBlank String provider,
                @NotBlank String accountNumber,
                String expiryDate,
                Boolean isDefault) {
}
