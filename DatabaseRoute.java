import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DatabaseRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange http) throws IOException {

        String method = http.getRequestMethod(); // GET, POST etc

        int id;
        // This gets the id from the url, example /database/7
        try {
            id = Integer.parseInt(http.getRequestURI().toString().replaceAll("/database/", ""));
        } catch (Exception e) {
            id = -1; // If no id is there it gives -1
        }

        String text;
        try {
            // Just copied some code to convert the input stream to String
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(
                    new InputStreamReader(http.getRequestBody(), Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            text = textBuilder.toString();
        } catch (Exception e) {
            text = "";
        }

        String response;
        int statusCode = 200;

        //A try catch because I don't validate the data sent in, so anything can happen
        try {
            switch (method) {
                case "GET":
                    if (id >= 0) {
                        response = Database.read(id);
                    } else {
                        response = Database.read().toString();
                    }
                    break;
                case "POST":
                    response = "created at id" + Database.create(text);
                    statusCode = 201; // Created
                    break;
                case "PUT":
                    Database.update(id, text);
                    response = "updated";
                    break;
                case "DELETE":
                    Database.delete(id);
                    response = "deleted";
                    break;
                default:
                    response = "Please use, GET, POST, PUT or DELETE";
                    statusCode = 405; // Method Not Allowed
                    break;
            }
        } catch (Exception e) {
            //If something goes wrong, just 404
            response = "Does not exist";
            statusCode = 404;
            System.out.println("Something went wrong");
        }

        http.sendResponseHeaders(statusCode, response.length());
        OutputStream os = http.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

}
