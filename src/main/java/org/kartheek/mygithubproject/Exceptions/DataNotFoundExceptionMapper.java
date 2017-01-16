package org.kartheek.mygithubproject.Exceptions;

import org.kartheek.mygithubproject.Models.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by kartheekvadlamani on 1/15/17.
 */

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "www.google.com");
        return Response.status(Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
