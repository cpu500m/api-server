package server.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.sun.net.httpserver.HttpExchange;
import server.code.Status;
import server.dto.Sum;
import server.dto.User;
import server.exception.UnSupportedMethodException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * data/user.json을 읽어서
 * post_count의 합을 출력.
 */
public class SumHandler extends JsonHttpHandler {
    private final String PROJECT_DIR = System.getProperty("user.dir");
    private ObjectMapper om = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    @Override
    public Sum getLogic(HttpExchange exchange) throws IOException {
        // 파일을 읽어옴
        String string = Files.readString(Path.of(PROJECT_DIR, "data/user.json"));

        // String -> 객체로 convert
        long result = 0;
        List<User> users = om.readValue(string, new TypeReference<List<User>>() {
        });

        for (User user : users) {
            result += user.getPostCount();
        }

        return new Sum(result);
    }

    @Override
    public <T> T postLogic(HttpExchange exchange) throws Exception {
        throw new UnSupportedMethodException(Status.UNSUPPORTED_MESSAGE);
    }
}
