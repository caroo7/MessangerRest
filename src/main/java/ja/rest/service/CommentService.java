package ja.rest.service;

import ja.rest.database.DatabaseInMemory;
import ja.rest.model.Comment;
import ja.rest.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseInMemory.getMessages();

    // GET all comments for message
    public List<Comment> getComments(long messageId) {
        return new ArrayList<>(messages.get(messageId).getComments().values());
    }

    // GET specific comment
    public Comment getComment(long messageId, long commentId) {
        Map<Long, Comment> commentsMap = messages.get(messageId).getComments();
        return commentsMap.get(commentId);
    }

    // POST
    public Comment createComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        long id = comments.size() + 1;
        comment.setId(id);
        comments.put(id, comment);
        return comment;
    }

    // PUT
    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if(comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    // DELETE
    public Comment removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }

}