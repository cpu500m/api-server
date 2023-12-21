package server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.code.Status;
import server.exception.CannotParseParamException;
import server.exception.UnSupportedMethodException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 공통 로직 수행 ( reponse 헤더 설정 및 body에 작성). 프론트 핸들러.
 */
public abstract class JsonHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();

        // Content-Type json으로.
        responseHeaders.set("Content-Type", "application/json");
        String response = "";
        try {
            response = convert2Json(exchange);
            exchange.sendResponseHeaders(Status.OK, response.getBytes().length);
        }catch (CannotParseParamException cpe){ // URI Query를 잘못 날린 경우엔 400대 코드를 반환.
            exchange.sendResponseHeaders(Status.CLIENT_ERROR, response.length());
        } catch (UnSupportedMethodException usme){ // 지원하지 않는 메서드
            exchange.sendResponseHeaders(Status.METHOD_NOT_ALLOWED , response.length());
        }
        catch (Exception e) { // error ( 여러 경우가 있고 checked면 unchecked로 변환하고 해야겠지만.. 일단 간략하게)
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
    private String convert2Json(HttpExchange exchange) throws Exception {
        ObjectMapper om = new ObjectMapper();
        if(exchange.getRequestMethod().equals("GET"))
            return om.writeValueAsString(this.getLogic(exchange));
        else if(exchange.getRequestMethod().equals("POST"))
            return om.writeValueAsString(this.postLogic(exchange));
        else throw new UnSupportedMethodException(Status.UNSUPPORTED_MESSAGE);
    }

    /**
     * 로직 수행 후 각각에 맞는 Object 반환.
     * get & post만 지원하게 해놨음. put, patch , delete는 음.. 나중에 필요를 느끼면 하는걸로
     */
    public abstract <T> T getLogic(HttpExchange exchange) throws Exception;
    public abstract <T> T postLogic(HttpExchange exchange) throws Exception;

}
