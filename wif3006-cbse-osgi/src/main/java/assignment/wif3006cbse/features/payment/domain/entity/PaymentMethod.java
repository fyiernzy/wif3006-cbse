package assignment.wif3006cbse.features.payment.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class PaymentMethod implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String type; // BANK, WALLET, CARD
    private String provider; // e.g., Maybank, GrabPay, Visa
    private String accountNumber;
    private String expiryDate; // MM/YY for cards
    private Boolean isDefault;
    private java.time.LocalDateTime createdAt;

    public PaymentMethod() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = java.time.LocalDateTime.now();
    }

    public PaymentMethod(String userId, String type, String provider, String accountNumber, String expiryDate,
            Boolean isDefault) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.provider = provider;
        this.accountNumber = accountNumber;
        this.expiryDate = expiryDate;
        this.isDefault = isDefault;
        this.createdAt = java.time.LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
