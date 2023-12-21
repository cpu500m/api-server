package server.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.sun.net.httpserver.HttpExchange;
import server.code.Status;
import server.dto.Info;
import server.exception.CannotParseParamException;
import server.exception.UnSupportedMethodException;

import java.net.URI;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 파라미터를 받아서 Json 형태로 출력.
 */
public class GetParamHandler extends JsonHttpHandler{

    ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    @Override
    public Info getLogic(HttpExchange exchange) throws Exception {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        try {
            String[] pairs = query.split("&");
            Info info = new Info();
            Map<String, String> data = info.getData();

            // 파라미터 쪼개서 넣음.
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                data.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            info.setCount(Long.valueOf(data.size()));
            return info;
        } catch (StringIndexOutOfBoundsException e) {
            throw new CannotParseParamException(e);
        }
    }

    @Override
    public <T> T postLogic(HttpExchange exchange) throws Exception {
        throw new UnSupportedMethodException(Status.UNSUPPORTED_MESSAGE);
    }
}
