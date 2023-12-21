package server.handler;

import com.sun.net.httpserver.HttpExchange;
import server.code.Status;
import server.dto.Start;
import server.exception.UnSupportedMethodException;


/**
 * OK 메시지 출력. 루트경로
 */
public class StartHandler extends JsonHttpHandler{
    @Override
    public Start getLogic(HttpExchange exchange) {
        if(exchange.getRequestMethod().equals("GET"))
            return new Start("OK");
        else throw new UnSupportedMethodException("지원하지 않는 메서드입니다.");
    }

    @Override
    public <T> T postLogic(HttpExchange exchange) throws Exception {
        throw new UnSupportedMethodException(Status.UNSUPPORTED_MESSAGE);
    }
}
