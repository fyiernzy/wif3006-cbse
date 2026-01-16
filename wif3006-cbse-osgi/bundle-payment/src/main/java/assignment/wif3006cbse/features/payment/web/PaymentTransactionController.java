package assignment.wif3006cbse.features.payment.web;

import assignment.wif3006cbse.features.payment.application.dto.transaction.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.dto.transaction.UpdatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.application.service.PaymentTransactionService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component(service = { PaymentTransactionController.class }, property = {
        "osgi.jaxrs.resource=true",
        "osgi.jaxrs.application.select=(osgi.jaxrs.name=main)",
})
@Path("/api/v1/payment-transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentTransactionController {

    @Reference
    private PaymentTransactionService paymentTransactionService;

    @POST
    public PaymentTransactionModel createPaymentTransaction(CreatePaymentTransactionModel createModel) {
        return paymentTransactionService.createPaymentTransaction(createModel);
    }

    @GET
    @Path("/{id}")
    public PaymentTransactionModel findPaymentTransactionById(@PathParam("id") String id) {
        return paymentTransactionService.findPaymentTransactionById(id);
    }

    @PUT
    public PaymentTransactionModel updatePaymentTransaction(UpdatePaymentTransactionModel updateModel) {
        return paymentTransactionService.updatePaymentTransaction(updateModel);
    }

    @DELETE
    @Path("/{id}")
    public void deletePaymentTransactionById(@PathParam("id") String id) {
        paymentTransactionService.deletePaymentTransactionById(id);
    }

    @GET
    @Path("/user/{userId}")
    public List<PaymentTransactionModel> findPaymentTransactionsByUserId(@PathParam("userId") String userId) {
        return paymentTransactionService.findPaymentTransactionsByUserId(userId);
    }

    @GET
    @Path("/project/{projectId}")
    public List<PaymentTransactionModel> findPaymentTransactionsByProjectId(@PathParam("projectId") String projectId) {
        return paymentTransactionService.findPaymentTransactionsByProjectId(projectId);
    }

    @GET
    @Path("/{id}/invoice")
    @Produces(MediaType.TEXT_PLAIN) // Invoice is text
    public String generateInvoice(@PathParam("id") String id) {
        return paymentTransactionService.generateInvoice(id);
    }
}
