package rest;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class ParseJson {
    public static String parce(String responseBody){
        JSONArray authors = new JSONArray(responseBody);
        StringBuilder allDescription = new StringBuilder();
        for (int i = 0; i < authors.length(); i++){
            JSONObject author = authors.getJSONObject(i);
            int authorId = author.getInt("authorId");
            JSONObject authorName  = author.getJSONObject("authorName");
            String first = authorName.getString("first");
            String authorDescription = author.getString("authorDescription");
            allDescription.append(authorId).append(" | ").append(first).append(" | ").append(authorDescription).append("\n");
            System.out.println(authorId + " | " + first +  " | " + authorDescription);
        }
        return allDescription.toString();
    }

    public static String parceAuthor(String responseBody){
        JSONObject author = new JSONObject(responseBody);
        JSONObject authorName  = author.getJSONObject("authorName");
        return  authorName.getString("first");
    }

    public static List<Integer> parceAuthorId(String responseBody){
        JSONArray albums = new JSONArray(responseBody);
        List<Integer> response = new ArrayList<>();
        for (int i = 0; i < albums.length(); i++){
            Integer album = albums.getJSONObject(i).getInt("authorId");
            response.add(album);
        }
        return response;
    }
}
