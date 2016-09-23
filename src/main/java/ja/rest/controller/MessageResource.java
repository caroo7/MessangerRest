package ja.rest.controller;

import ja.rest.exception.DataNotFoundException;
import ja.rest.model.Message;
import ja.rest.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    private static MessageService service = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
        System.out.println("JSON method invocaction");
        if (filterBean.getYear() > 0) {
            return service.getMessagesByYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getNumber() > 0) {
            return service.getPaginatedMessages(filterBean.getStart(), filterBean.getNumber());
        }
        return service.getMessages();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean) {
        System.out.println("XML method invocaction");
        if (filterBean.getYear() > 0) {
            return service.getMessagesByYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getNumber() > 0) {
            return service.getPaginatedMessages(filterBean.getStart(), filterBean.getNumber());
        }
        return service.getMessages();
    }

    @GET
    @Path(value = "/{messageId}")
    public Message getMessage(@Context UriInfo uriInfo, @PathParam("messageId") long messageId) throws DataNotFoundException {
        Message message = service.getMessage(messageId);
        if (message == null) {
            throw new DataNotFoundException("Message with id : " + messageId + " not found");
        }
        message.clearLinks();
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResources")
                .resolveTemplate("messageId", message.getId())
                .build()
                .toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
    }

    /* example
    {
    "author" : "adas",
    "created" : "2016-09-19T15:57:51.337+02:00",
    "message" : "Taki sobie Adas"
    }
    */

    @POST
    public Response addMessage(@Context UriInfo uriInfo, Message message) {
        Message newMessage = service.createMessage(message);
        String newId = String.valueOf(message.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

        return Response.created(uri)
                .entity(newMessage)
                .encoding("UTF-16")
                .build();
    }

    @PUT
    @Path(value = "/{messageId}")
    public Message updateMessage(@PathParam(value = "messageId") long messageId, Message message) {
        message.setId(messageId);
        return service.updateMessage(message);
    }

    @DELETE
    @Path(value = "/{messageId}")
    public void deleteMessage(@PathParam(value = "messageId") long messageId) {
        service.deleteMessage(messageId);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResources() {
        return new CommentResource();
    }

}