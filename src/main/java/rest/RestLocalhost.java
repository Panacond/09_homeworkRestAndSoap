package rest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestLocalhost {
    private HttpURLConnection connection;
    private int status;
    public String readRest(String response, String addResponse){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        String allData;
        try {
            URL url = new URL("http://localhost:8080" + addResponse);
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod(response);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            status = connection.getResponseCode();
            System.out.println(status);

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
//            System.out.println(responseContent.toString());
            allData = responseContent.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            allData = "0";
        } catch (IOException e) {
            e.printStackTrace();
            allData = "1";
        } finally {
            connection.disconnect();
        }
        return allData;
    }
    public static String parce(String responseBody){
        JSONArray authors = new JSONArray(responseBody);
        for (int i = 0; i < authors.length(); i++){
            JSONObject author = authors.getJSONObject(i);
            int authorId = author.getInt("authorId");
            JSONObject authorName  = author.getJSONObject("authorName");
            String first = authorName.getString("first");
            String authorDescription = author.getString("authorDescription");
            System.out.println(authorId + " " + first +  " || " + authorDescription);
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public static String parceAuthor(String responseBody){
        JSONObject author = new JSONObject(responseBody);
        int authorId = author.getInt("authorId");
        JSONObject authorName  = author.getJSONObject("authorName");
        String first = authorName.getString("first");
        String authorDescription = author.getString("authorDescription");
        System.out.println(authorId + " " + first +  " || " + authorDescription);
        return  first;
    }

    public String parceTitle(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
//        List response = (List) albums;
        String result = "";
        for (int i = 0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            result = result + " ;" + title;
//            System.out.println(id + " " + userId + " " + title);
        }
        return result;
    }

    public List<JSONObject> parceList(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        List<JSONObject> response = new ArrayList<>();
        for (int i = 0; i < albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            response.add(album);
        }
        return response;
    }
}
