package ja.rest.exception;

import ja.rest.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage message = new ErrorMessage(throwable.getMessage(), 500);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .encoding("UTF-16")
                .entity(message)
                .build();
    }

}