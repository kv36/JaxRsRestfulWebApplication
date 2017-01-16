package org.kartheek.mygithubproject.Models;

import java.util.Date;

/**
 * Created by kartheekvadlamani on 1/15/17.
 */
public class Comment {

    private long id;
    private String message;
    private Date created;
    private String author;

    public Comment() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
