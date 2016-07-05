package root.model.matrix.exception;


public class MultipleException extends Exception {

    public MultipleException() {}

    public MultipleException(String message) {
        super(message);
    }

    public MultipleException(String message, Throwable cause) {
        super(message, cause);
    }
}
