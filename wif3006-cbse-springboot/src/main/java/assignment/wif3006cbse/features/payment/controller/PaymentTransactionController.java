package assignment.wif3006cbse.features.payment.controller;

import assignment.wif3006cbse.features.payment.dto.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.service.PaymentTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-transaction")
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;

    @PostMapping
    public PaymentTransactionModel createPaymentTransaction(
            @RequestBody @Valid CreatePaymentTransactionModel createPaymentTransactionModel) {
        return paymentTransactionService.createPaymentTransaction(createPaymentTransactionModel);
    }

    @GetMapping("/{id}")
    public PaymentTransactionModel findPaymentTransactionById(@PathVariable java.util.UUID id) {
        return paymentTransactionService.findPaymentTransactionById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentTransactionModel>> findPaymentTransactionsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(paymentTransactionService.findPaymentTransactionsByUserId(userId));
    }

    @GetMapping("/project/{projectId}")
    public List<PaymentTransactionModel> findPaymentTransactionsByProjectId(@PathVariable String projectId) {
        return paymentTransactionService.findPaymentTransactionsByProjectId(projectId);
    }

    @GetMapping(value = "/{id}/invoice", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> generateInvoice(@PathVariable java.util.UUID id) {
        return ResponseEntity.ok(paymentTransactionService.generateInvoice(id));
    }

    @PutMapping
    public PaymentTransactionModel updatePaymentTransaction(
            @RequestBody @Valid UpdatePaymentTransactionModel updatePaymentTransactionModel) {
        return paymentTransactionService.updatePaymentTransaction(updatePaymentTransactionModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentTransaction(@PathVariable java.util.UUID id) {
        return paymentTransactionService.deletePaymentTransactionById(id);
    }
}
