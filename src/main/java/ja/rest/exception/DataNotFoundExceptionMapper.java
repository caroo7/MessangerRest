package ja.rest.exception;

import ja.rest.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
        return Response
                .status(Response.Status.NOT_FOUND)
                .encoding("UTF-16")
                .entity(errorMessage)
                .build();
    }
}