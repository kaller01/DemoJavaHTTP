import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class Route implements HttpHandler {
    
    @Override
    public void handle(HttpExchange http) throws IOException {
        String response = "Hello World";
        http.sendResponseHeaders(200, response.length());
        OutputStream os = http.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
