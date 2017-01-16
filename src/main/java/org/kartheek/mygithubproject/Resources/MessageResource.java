package org.kartheek.mygithubproject.Resources;

import org.kartheek.mygithubproject.BeanParam.BeanParamClass;
import org.kartheek.mygithubproject.Models.Message;
import org.kartheek.mygithubproject.Service.MessageService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by kartheekvadlamani on 1/6/17.
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam BeanParamClass filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }

        if (filterBean.getStart() > 0 && filterBean.getSize() > 1 ) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize() );
        }
        return messageService.getAllMessages();
    }


    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXMLMessages(@BeanParam BeanParamClass filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }

        if (filterBean.getStart() > 0 && filterBean.getSize() > 1 ) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize() );
        }
        return messageService.getAllMessages();
    }
//    @POST
//    public Message addMessage(Message message) {
//        return messageService.addMessage(message);
//    }

    //This is to be done when you  dont want the URI to be hardcoded.
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException{
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                .entity(newMessage)
                .build();
    }

    // This is a hard coded uri post example method
    @POST
    public Response addMessage(Message message) throws URISyntaxException {
        Message newMessage = messageService.addMessage(message);
        return Response.created(new URI("/messenger/webapi/messages" + newMessage.getId()))
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")Long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId")Long messageId) {
        messageService.removeMessage(messageId);
    }


    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId")Long messageId, @Context UriInfo uriInfo ) {
        Message message = messageService.getMessage(messageId);
        message.addLink(getUriForSelf(uriInfo, message), "Self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;

    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {

        String uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
        return uri;
    }


    private String getUriForComments(UriInfo uriInfo, Message message) {

        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentsResource.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri .toString();
    }


    private String getUriForProfile(UriInfo uriInfo, Message message) {

        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build();

        return uri.toString();
    }


    @GET
    @Path("/{messageId}/comments")
    public CommentsResource getCommentResource(){
        return new CommentsResource();
    }
}
