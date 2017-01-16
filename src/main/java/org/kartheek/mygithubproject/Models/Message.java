package org.kartheek.mygithubproject.Models;

import java.util.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by kartheekvadlamani on 1/6/17.
 */
@XmlRootElement
public class Message {

    private Long id;
    private String message;
    private Date created;
    private String author;
    private Map<Long,Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Message() {}

    public Message(Long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.author = author;
        created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String Url, String rel) {
        Link link = new Link();
        link.setLink(Url);
        link.setRel(rel);
        links.add(link);
    }
}
