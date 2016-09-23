package ja.rest.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

    @GET
    public String getParamsUsingAnnotations(@HeaderParam(value = "authToken") String authToken, @MatrixParam("matrixParam") String matrixParam) {
        return "Header auth param: " + authToken + ", matrix param: " + matrixParam;
    }

    @GET
    @Path("/context")
    public String retrieveContextParameters(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        return "URI : " + uriInfo.getAbsolutePath().toString() + ", headers : " + httpHeaders.getCookies().toString();
    }

}