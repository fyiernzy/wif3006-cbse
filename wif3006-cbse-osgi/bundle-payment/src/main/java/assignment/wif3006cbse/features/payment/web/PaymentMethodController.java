package assignment.wif3006cbse.features.payment.web;

import assignment.wif3006cbse.features.payment.application.dto.method.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.dto.method.UpdatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.application.service.PaymentMethodService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = { PaymentMethodController.class }, property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/payment-methods")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentMethodController {

    @Reference
    private PaymentMethodService paymentMethodService;

    @POST
    public PaymentMethodModel createPaymentMethod(CreatePaymentMethodModel createModel) {
        return paymentMethodService.createPaymentMethod(createModel);
    }

    @GET
    @Path("/{id}")
    public PaymentMethodModel findPaymentMethodById(@PathParam("id") String id) {
        return paymentMethodService.findPaymentMethodById(id);
    }

    @PUT
    public PaymentMethodModel updatePaymentMethod(UpdatePaymentMethodModel updateModel) {
        return paymentMethodService.updatePaymentMethod(updateModel);
    }

    @DELETE
    @Path("/{id}")
    public void deletePaymentMethodById(@PathParam("id") String id) {
        paymentMethodService.deletePaymentMethodById(id);
    }

    @GET
    @Path("/user/{userId}")
    public List<PaymentMethodModel> findPaymentMethodsByUserId(@PathParam("userId") String userId) {
        return paymentMethodService.findAllPaymentMethodsByUserId(userId);
    }
}
