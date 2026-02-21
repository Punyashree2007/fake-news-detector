import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/detect", (exchange -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder body = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    body.append(line);
                }
                br.close();

                String[] parts = body.toString().split("\\|");
                String result = "Error";
                if (parts.length >= 3) {
                    NewsArticle article = new NewsArticle(parts[0], parts[1], parts[2]);
                    FakeNewsDetector detector = new FakeNewsDetector();
                    result = detector.detect(article);
                }

                exchange.sendResponseHeaders(200, result.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(result.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); 
            }
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 5000...");
    }
}