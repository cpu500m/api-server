package server.code;

/**
 * HttpStatus 라이브러리 땡겨오면 되겠지만 그냥 간단한 응답상태들만 써볼거라 만듦.
 */
public class Status {
    static public final int OK = 200;
    static public final int REDIRECT = 300;
    static public final int CLIENT_ERROR = 400;
    static public final int CANNOT_FOUND = 404;
    static public final int SERVER_ERROR = 500;
}
