package org.kartheek.mygithubproject.Resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Created by kartheekvadlamani on 1/8/17.
 */
@Path("/paramExammple")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ParamExampleResource {

    @GET
    @Path("/annotations")
    public String  getParamsUsingAnnotations(@MatrixParam("param")String matrixParam,
                                             @HeaderParam("authSessionId")String header,
                                             @CookieParam("name")String cookie) {

        return "MatrixPram: " + matrixParam + "HeaderParam :" + header + "CookieParam :" + cookie;
    }

    @GET
    @Path("conntext")
    public String getparamsUsingContext(@Context UriInfo uriInfo, HttpHeaders headers) {

       String path = uriInfo.getAbsolutePath().toString();
        String cookies = headers.getCookies().toString();
         return "path :" + path + "cookies :" + cookies;
    }

}
