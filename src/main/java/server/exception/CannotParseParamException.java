package server.exception;

public class CannotParseParamException extends RuntimeException{
    public CannotParseParamException() {
    }

    public CannotParseParamException(String message) {
        super(message);
    }

    public CannotParseParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotParseParamException(Throwable cause) {
        super(cause);
    }
}
