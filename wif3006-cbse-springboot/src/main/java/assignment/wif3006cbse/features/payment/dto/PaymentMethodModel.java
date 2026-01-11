package assignment.wif3006cbse.features.payment.dto;

public record PaymentMethodModel(
        java.util.UUID id,
        String userId,
        String type,
        String provider,
        String accountNumber,
        String expiryDate,
        Boolean isDefault) {
}
