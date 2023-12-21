package server.handler;

import com.sun.net.httpserver.HttpExchange;
import server.code.Status;
import server.dto.Info;
import server.exception.UnSupportedMethodException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BodyHandler extends JsonHttpHandler{
    @Override
    public Info getLogic(HttpExchange exchange) throws Exception {
        throw new UnSupportedMethodException(Status.UNSUPPORTED_MESSAGE);
    }

    @Override
    public Info postLogic(HttpExchange exchange) throws Exception {
        InputStream is = exchange.getRequestBody();
        InputStreamReader reader = new InputStreamReader(is);
        StringBuilder sb = new StringBuilder();

        for (int c = reader.read(); c != -1; c = reader.read()) {
            sb.append((char)c);
        }
        String body = sb.toString();
        System.out.println("body = " + body);

        return null;
    }
}
