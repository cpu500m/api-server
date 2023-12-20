package server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.code.Status;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 공통 로직 수행 ( reponse 헤더 설정 및 body에 작성)
 */
public abstract class JsonHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();

        // Content-Type json으로.
        responseHeaders.set("Content-Type", "application/json");

        String response = "";
        try {
            response = convert2Json();
            exchange.sendResponseHeaders(Status.OK, response.getBytes().length);
        } catch (Exception e) { // error ( 여러 경우가 있고 checked면 unchecked로 변환하고 해야겠지만.. 일단 간략하게)
            exchange.sendResponseHeaders(Status.SERVER_ERROR, response.length());
        } finally { // responseBody에 응답 결과를 쓰고 닫아준다.
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response.getBytes());
            responseBody.close();
        }
    }

    /**
     * Convert
     *  Object -> Json (String)
     */
    private String convert2Json() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(this.handle());
    }

    /**
     * 각각에 맞는 Object 반환
     */
    public abstract <T> T handle();
}
