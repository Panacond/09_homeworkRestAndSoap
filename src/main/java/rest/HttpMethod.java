package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpMethod {
    private int status;
    private String responseJsonBody;
    private final String selectedMethod;
    private String jsonInputString;
    public HttpMethod(method setMethod, String addPart){
        if (setMethod == method.GET){
            selectedMethod = "GET";
        } else {
            selectedMethod = "DELETE";
        }
        runResponse(setMethod, addPart);
    }

    public HttpMethod(method setMethod, String addPart, String jsonInputString) {
        if (setMethod == method.POST) {
            selectedMethod = "POST";
        } else {
            selectedMethod = "PUT";
        }
        this.jsonInputString = jsonInputString;
        runResponse(setMethod, addPart);
    }

    private void runResponse(method setMethod ,String addPart) {
        try {
            URL url = new URL("http://localhost:8080"+ addPart);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(selectedMethod);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if (setMethod == method.POST || setMethod == method.PUT) {
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                try (
                        OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(UTF_8);
                    os.write(input, 0, input.length);
                }
            }
            status = connection.getResponseCode();
            System.out.println(status);
            StringBuilder responseContent = new StringBuilder();
            BufferedReader reader;
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            String line;
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
            responseJsonBody = responseContent.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseJsonBody() {
            return responseJsonBody;
    }

    public int getStatus() {
        return status;
    }
}
