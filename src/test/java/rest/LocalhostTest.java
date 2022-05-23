package rest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LocalhostTest {
    @Test
    public void getAuthors() {
        RestLocalhostMethodGet item = new RestLocalhostMethodGet();

        String result = item.readRest("GET", "/api/library/authors");
        List<JSONObject> authors = item.parceList(result);
        String parce = item.parce(result);
        Assert.assertEquals(authors.size(), 10, authors.size() + " = 10");
        Assert.assertTrue(parce.length()>1000,"letters is more 1000 chapters");
    }



    @Test
    public void postAuthor() {
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"Sting\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        for (int i = 0; i < 2; i++) {
            item.setMethod("POST", "/author", jsonInputString);
        }
        Assert.assertEquals(item.getStatus(), 409, item.getStatus() + " = 409");
    }



    @Test
    public void bigTestData(){
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"string\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        item.setMethod("POST", "/author", jsonInputString);

        RestLocalhostMethodGet itemSecond = new RestLocalhostMethodGet();
        itemSecond.readRest("DELETE", "/api/library/author/1013");
        Assert.assertEquals(itemSecond.getStatus(), 204, itemSecond.getStatus() +  "= 204");
    }

    @Test
    public void putAuthor() {
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"go\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        item.setMethod("PUT", "/author", jsonInputString);
        Assert.assertEquals(item.getStatus(), 200, item.getStatus() + " = 200");
        String response =  item.getResponseString();
        jsonInputString = jsonInputString.replace(" ", "");
        Assert.assertEquals(response, jsonInputString);
    }

    @Test
    public void getAuthorId() {
        RestLocalhostMethodGet item = new RestLocalhostMethodGet();
        String result = item.readRest("GET", "/api/library/author/1013");
        String first = RestLocalhostMethodGet.parceAuthor(result);
        Assert.assertEquals(first, "go", first + " = go");
    }
}
