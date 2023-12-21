package server.handler;

import com.sun.net.httpserver.HttpExchange;
import server.code.Status;
import server.dto.Info;
import server.exception.CannotParseParamException;
import server.exception.UnSupportedMethodException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;

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

        // 첫 공백 버려줌
        reader.read();

        for (int c = reader.read(); c != -1; c = reader.read()) {
            sb.append((char)c);
        }

        String body = sb.toString();

        try {
            String[] pairs = body.split("\\{|&|}|,");
            Info info = new Info();
            Map<String, String> data = info.getData();

            // 파라미터 쪼개서 넣음.
            for (String pair : pairs) {
                int idx = pair.indexOf(":");
                String key = pair.substring(0, idx);
                key =key.replaceAll("[^a-zA-Z0-9]","");
                String value = pair.substring(idx+1);
                value = value.replaceAll("[^a-zA-Z0-9]","");


                data.put(URLDecoder.decode(key, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
            }
            info.setCount(Long.valueOf(data.size()));
            return info;
        } catch (StringIndexOutOfBoundsException e) {
            throw new CannotParseParamException(e);
        }
    }
}
