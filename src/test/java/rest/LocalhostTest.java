package rest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LocalhostTest {
    @Test
    public void getAuthors() {
        RestLocalhost item = new RestLocalhost();

        String result = item.readRest("GET", "/api/library/authors");
        List<JSONObject> authors = item.parceList(result);
        Assert.assertEquals(authors.size(), 10, authors.size() + " = 10");
    }

    @Test
    public void getAuthorId() {
        RestLocalhost item = new RestLocalhost();
        String result = item.readRest("GET", "/api/library/author/140");
        String first = RestLocalhost.parceAuthor(result);
        Assert.assertEquals(first, "Lauriane", first + " = Lauriane");
    }

    @Test
    public void postAuthor() {
        RestLocalHostAllMethods item = new RestLocalHostAllMethods();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"string\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        item.setMethod("POST", "/author", jsonInputString);
        Assert.assertEquals(item.getStatus(), 409, item.getStatus() + " = 409");
    }



    @Test
    public void bigTestData(){
        RestLocalHostAllMethods item = new RestLocalHostAllMethods();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"string\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        item.setMethod("POST", "/author", jsonInputString);

        RestLocalhost itemSecond = new RestLocalhost();
        itemSecond.readRest("DELETE", "/api/library/author/1013");
        Assert.assertEquals(itemSecond.getStatus(), 204, itemSecond.getStatus() +  "= 204");
    }

    @Test
    public void putAuthor() {
        RestLocalHostAllMethods item = new RestLocalHostAllMethods();
        String jsonInputString = "{ \"authorId\": 1013, \"authorName\": { \"first\": \"go\", \"second\": \"string\" }, \"nationality\": \"string\", \"birth\": { \"date\": \"1973-03-28\", \"country\": \"string\", \"city\": \"string\" }, \"authorDescription\": \"string\"}";
        item.setMethod("PUT", "/author", jsonInputString);
        Assert.assertEquals(item.getStatus(), 200, item.getStatus() + " = 200");
    }
}
