package ja.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String message;
    private int status;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, int status) {
        super();
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
