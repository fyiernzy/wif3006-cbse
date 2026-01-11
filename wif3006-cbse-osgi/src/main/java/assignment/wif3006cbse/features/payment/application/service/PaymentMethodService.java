package assignment.wif3006cbse.features.payment.application.service;

import assignment.wif3006cbse.features.payment.application.dto.method.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.UpdatePaymentMethodModel;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodModel createPaymentMethod(CreatePaymentMethodModel createPaymentMethodModel);

    PaymentMethodModel findPaymentMethodById(String id);

    PaymentMethodModel updatePaymentMethod(UpdatePaymentMethodModel updatePaymentMethodModel);

    void deletePaymentMethodById(String id);

    List<PaymentMethodModel> findAllPaymentMethodsByUserId(String userId);
}
