package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.nio.charset.StandardCharsets.*;

public class RestLocalHostMethodPost {
    private int status;
    private String responseString;

    public void setMethod(String method, String addPart, String jsonInputString) {
        try {
            URL url = new URL("http://localhost:8080/api/library"+ addPart);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
//            String jsonInputString = "{ \"authorId\": 1012, \"authorName\": { \"first\": \"string\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
            try (
                    OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(UTF_8);
                os.write(input, 0, input.length);
            }
            status = connection.getResponseCode();
            System.out.println(status);
            try (
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseString = response.toString();
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseString() {
        if (status> 299){
            return "Error not response";
        } else {
            return responseString;
        }
    }

    public int getStatus() {
        return status;
    }
}
