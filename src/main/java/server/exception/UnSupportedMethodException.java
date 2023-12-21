package server.exception;

public class UnSupportedMethodException extends RuntimeException{
    public UnSupportedMethodException() {
    }

    public UnSupportedMethodException(String message) {
        super(message);
    }

    public UnSupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnSupportedMethodException(Throwable cause) {
        super(cause);
    }
}
