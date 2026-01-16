package assignment.wif3006cbse.shared.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(new ErrorResponse(exception.getMessage()))
            .build();
    }
}