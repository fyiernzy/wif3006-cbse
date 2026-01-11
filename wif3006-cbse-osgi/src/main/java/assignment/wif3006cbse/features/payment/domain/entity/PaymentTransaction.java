package assignment.wif3006cbse.features.payment.domain.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentTransaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String userId;
    private String projectId;
    private String paymentMethodId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String status; // PENDING, COMPLETED, FAILED
    private String invoiceId;

    public PaymentTransaction() {
        this.id = UUID.randomUUID().toString();
        this.transactionDate = LocalDateTime.now();
    }

    public PaymentTransaction(String userId, String projectId, String paymentMethodId, BigDecimal amount,
            String status) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.projectId = projectId;
        this.paymentMethodId = paymentMethodId;
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.status = status;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
