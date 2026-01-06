package assignment.wif3006cbse.features.profile.web;

import assignment.wif3006cbse.features.profile.application.dto.product.CreateProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.ProductModel;
import assignment.wif3006cbse.features.profile.application.dto.product.UpdateProductModel;
import assignment.wif3006cbse.features.profile.application.service.ProductService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component(service = {ProductController.class}, property = {
    "osgi.jaxrs.resource=true",
    "osgi.jaxrs.application.select=(osgi.jaxrs.name=profile)"
})
@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Reference
    private ProductService productService;

    @POST
    public Response createProduct(CreateProductModel createProductModel) {
        try {
            ProductModel product = productService.createProduct(createProductModel);
            return Response.status(201).entity(product).build();
        } catch (Exception e) {
            return Response.status(400)
                .entity(Map.of("error", "Failed to create product: " + e.getMessage()))
                .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") String id) {
        try {
            ProductModel product = productService.findProductById(id);
            return Response.ok(product).build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GET
    @Path("/user/{userId}")
    public Response getProductsByUserId(@PathParam("userId") String userId) {
        try {
            List<ProductModel> products = productService.findAllByUserId(userId);
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to retrieve products: " + e.getMessage()))
                .build();
        }
    }

    @GET
    @Path("/category/{category}")
    public Response getProductsByCategory(@PathParam("category") String category) {
        try {
            List<ProductModel> products = productService.findAllByCategory(category);
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to retrieve products: " + e.getMessage()))
                .build();
        }
    }

    @PUT
    public Response updateProduct(UpdateProductModel updateProductModel) {
        try {
            ProductModel product = productService.updateProduct(updateProductModel);
            return Response.ok(product)
                .entity(Map.of("message", "Product updated successfully", "product", product))
                .build();
        } catch (IllegalArgumentException e) {
            return Response.status(404)
                .entity(Map.of("error", e.getMessage()))
                .build();
        } catch (Exception e) {
            return Response.status(400)
                .entity(Map.of("error", "Invalid data: " + e.getMessage()))
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        try {
            productService.deleteProduct(id);
            return Response.ok()
                .entity(Map.of("message", "Product deleted successfully"))
                .build();
        } catch (Exception e) {
            return Response.status(500)
                .entity(Map.of("error", "Failed to delete product: " + e.getMessage()))
                .build();
        }
    }
}
