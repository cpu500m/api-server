package server;

import com.sun.net.httpserver.HttpServer;
import server.handler.BodyHandler;
import server.handler.GetParamHandler;
import server.handler.StartHandler;
import server.handler.SumHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        // 동시 request 500 제한.
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 500);

        addHandler(server);

        // thread 별렬 처리 가능하도록 executor 지정.
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

    private static void addHandler(HttpServer server) {
        // 1 .기본 GET 요청.
        server.createContext("/", new StartHandler());

        // 2 . GET 요청이 들어옴 + server 메모리에 있는 data를 읽어들인 결과를 API 반환.
        server.createContext("/sum", new SumHandler());

        // 3. GET 요청이 들어옴 + parameter 처리
        server.createContext("/param", new GetParamHandler());
        // 4. POST 요청이 들어옴 + parameter 처리 -- 이거는 뭐 그냥 RequestMethod가 Post로 바뀐것 뿐이니 그냥 3번이랑 비슷..
        // 스킵!

        // 5. POST 요청이 들어옴 + Json 형태 requestBody 처리하여 결과를 반환해야 함.
        server.createContext("/post", new BodyHandler());

    }
}
