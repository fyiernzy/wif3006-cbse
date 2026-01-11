package assignment.wif3006cbse.features.payment.controller;

import assignment.wif3006cbse.features.payment.dto.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.service.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public PaymentMethodModel createPaymentMethod(
            @RequestBody @Valid CreatePaymentMethodModel createPaymentMethodModel) {
        return paymentMethodService.createPaymentMethod(createPaymentMethodModel);
    }

    @GetMapping("/{id}")
    public PaymentMethodModel findPaymentMethodById(@PathVariable java.util.UUID id) {
        return paymentMethodService.findPaymentMethodById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentMethodModel>> findPaymentMethodsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(paymentMethodService.findAllPaymentMethodsByUserId(userId));
    }

    @PutMapping
    public PaymentMethodModel updatePaymentMethod(
            @RequestBody @Valid UpdatePaymentMethodModel updatePaymentMethodModel) {
        return paymentMethodService.updatePaymentMethod(updatePaymentMethodModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable java.util.UUID id) {
        return paymentMethodService.deletePaymentMethodById(id);
    }
}
