import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Main {
    public static void main(String[] args) throws Exception {
        String hostname = "192.168.2.213";
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
        server.createContext("/", new Route()); //Route
        server.createContext("/secret", new SecretRoute());
        server.createContext("/database", new DatabaseRoute());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started at " + hostname+":"+port);
    }
}
