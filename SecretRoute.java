import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class SecretRoute implements HttpHandler {
    
    @Override
    public void handle(HttpExchange http) throws IOException {
        
        String auth =(String) http.getRequestHeaders().get("Authorization").toArray()[0];
        System.out.println(auth);
        if(auth.equals("basgrupp4")){
            String response = "<h1>You are now authorized</h1>";
            http.sendResponseHeaders(200, response.length());
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            String response = "<h1>Lmao no access for you</h1>";
            http.sendResponseHeaders(403, response.length());
            OutputStream os = http.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
        
    }
}
