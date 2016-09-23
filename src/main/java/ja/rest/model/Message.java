package ja.rest.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

@XmlRootElement
public class Message {

    private long id;
    private String author;
    private Date created;
    private String message;
    private Map<Long, Comment> comments = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public Message() {
    }

    public Message(long id, String author, Date created, String message) {
        this.id = id;
        this.author = author;
        this.created = created;
        this.message = message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreated() {
        return created;
    }

    public String getMessage() {
        return message;
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

    public void clearLinks() {
        links.clear();
    }

    public void addLink(String url, String rel){
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

}