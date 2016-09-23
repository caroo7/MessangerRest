package ja.rest.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -806217246538483257L;

    public DataNotFoundException(String message) {
        super(message);
    }

}