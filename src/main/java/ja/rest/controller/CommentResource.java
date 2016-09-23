package ja.rest.controller;

import ja.rest.model.Comment;
import ja.rest.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class CommentResource {

    private static CommentService service = new CommentService();

    @GET
    public List<Comment> getComments(@PathParam("messageId") long messageId) {
        return service.getComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return service.getComment(messageId, commentId);
    }

    @POST
    public Comment createComment(@PathParam("messageId") long messageId, Comment comment) {
        return service.createComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return service.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
        return service.removeComment(messageId, commentId);
    }

}