package org.kartheek.mygithubproject.Service;

import org.kartheek.mygithubproject.DatabaseClass.DatabaseClass;
import org.kartheek.mygithubproject.Models.Comment;
import org.kartheek.mygithubproject.Models.ErrorMessage;
import org.kartheek.mygithubproject.Models.Message;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Created by kartheekvadlamani on 1/15/17.
 */
public class CommentService {

    public Map<Long, Message> messages = DatabaseClass.getMessages();

    public ArrayList<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(long commentId, long messageId) {

        ErrorMessage errorMessage = new ErrorMessage("NOT FOUND", 404, "www.google.com");
        Response response =  Response.status(Status.NOT_FOUND)
                .entity(errorMessage)
                .build();

        Message message = messages.get(messageId);
        if (message == null) {
            throw new WebApplicationException(response);
        }

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new WebApplicationException(response);
        }
        return comment;
    }

    public Comment addComment(Comment comment, long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }


    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long commentId, long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }
}
