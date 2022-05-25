package rest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import soap.FileRead;

import java.util.List;

public class RestTest {
    @Test (enabled = false)
    public void getAuthors() {
        RestLocalhostMethodGet item = new RestLocalhostMethodGet();

        String result = item.readRest("GET", "/api/library/authors");
        List<JSONObject> authors = item.parceList(result);
        String parce = item.parce(result);
        Assert.assertEquals(authors.size(), 10, authors.size() + " = 10");
        Assert.assertTrue(parce.length() > 1000, "letters is more 1000 chapters");
    }


    @Test (enabled = false)
    public void postAuthor() {
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        jsonInputString = jsonInputString.replace("0", "1013");
        jsonInputString = jsonInputString.replace("first_sting", "Silver");

        item.setMethod("POST", "/author", jsonInputString);
        item.setMethod("POST", "/author", jsonInputString);
        Assert.assertEquals(item.getStatus(), 409, item.getStatus() + " = 409");
    }


    @Test
    public void bigTestData() {
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        jsonInputString = jsonInputString.replace("0", "1013");
        jsonInputString = jsonInputString.replace("first_sting", "Silver");
        item.setMethod("POST", "/author", jsonInputString);

        RestLocalhostMethodGet itemSecond = new RestLocalhostMethodGet();
        itemSecond.readRest("DELETE", "/api/library/author/1013");
        Assert.assertEquals(itemSecond.getStatus(), 204, itemSecond.getStatus() + "= 204");
    }

    @Test (enabled = false)
    public void putAuthor() {
        RestLocalHostMethodPost item = new RestLocalHostMethodPost();
        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        jsonInputString.replace("0", "1013");
        jsonInputString.replace("first_sting", "Silver");
        item.setMethod("PUT", "/author/", jsonInputString);
        Assert.assertEquals(item.getStatus(), 200, item.getStatus() + " = 200");
        String response = item.getResponseString();
        jsonInputString = jsonInputString.replace(" ", "");
        jsonInputString = jsonInputString.replace("\n", "");
        Assert.assertEquals(response, jsonInputString);
    }

    @Test (enabled = false)
    public void getAuthorId() {
        RestLocalhostMethodGet item = new RestLocalhostMethodGet();
        String result = item.readRest("GET", "/api/library/author/0");
        String first = RestLocalhostMethodGet.parceAuthor(result);
        Assert.assertEquals(first, "go", first + " = go");
    }
}
