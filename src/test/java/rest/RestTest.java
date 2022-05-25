package rest;

import org.testng.Assert;
import org.testng.annotations.Test;
import soap.FileRead;
import soap.RegexXml;
import java.util.List;
import static rest.ParseJson.parceAuthor;

public class RestTest {
    @Test
    public void getAuthors() {
        HttpMethod httpGet = new HttpMethod(method.GET, "/api/library/authors?&pagination=false&");
        Assert.assertEquals(httpGet.getStatus(),200);
        List<Integer> authors = ParseJson.parceAuthorId(httpGet.getResponseJsonBody());
        System.out.println(authors);
        Assert.assertFalse(authors.isEmpty(), "List isn't empty");
    }


    @Test
    public void postAuthor() {
        HttpMethod httpGet = new HttpMethod(method.GET, "/api/library/authors?&pagination=false&");
        Assert.assertEquals(httpGet.getStatus(),200);
        List<Integer> authors = ParseJson.parceAuthorId(httpGet.getResponseJsonBody());
        String nextAuthor = Integer.toString(authors.size());

        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        String first = "Silver";
        jsonInputString = jsonInputString.replace("0,", nextAuthor +",");
        jsonInputString = jsonInputString.replace("first_sting", first);

        httpGet = new HttpMethod(method.POST, "/api/library/author",jsonInputString);
        Assert.assertEquals(httpGet.getStatus(), 201);
        String firstResponse =  new RegexXml("(\"first\":\")([^\"]*)",httpGet.getResponseJsonBody(), 2).result;
        Assert.assertEquals(first, firstResponse);

        httpGet = new HttpMethod(method.POST,"/api/library/author", jsonInputString);
        Assert.assertEquals(httpGet.getStatus(), 409);
    }


    @Test
    public void deleteAuthor() {
        HttpMethod httpGet = new HttpMethod(method.GET, "/api/library/authors?&pagination=false&");
        Assert.assertEquals(httpGet.getStatus(),200);
        List<Integer> authors = ParseJson.parceAuthorId(httpGet.getResponseJsonBody());
        String nextAuthor = Integer.toString(authors.get(3));

        httpGet = new HttpMethod(method.DELETE, "/api/library/author/"+nextAuthor +"?&forcibly=true&");
        Assert.assertEquals(httpGet.getStatus(),204);

        httpGet = new HttpMethod(method.GET, "/api/library/authors?&pagination=false&");
        Assert.assertEquals(httpGet.getStatus(),200);
        List<Integer> authorsSecond = ParseJson.parceAuthorId(httpGet.getResponseJsonBody());
        Assert.assertNotEquals(authors.get(3), authorsSecond.get(3), "Items of lists is single!");
    }

    @Test
    public void putAuthor() {
        HttpMethod httpGet = new HttpMethod(method.GET, "/api/library/authors?&pagination=false&");
        Assert.assertEquals(httpGet.getStatus(),200);
        List<Integer> authors = ParseJson.parceAuthorId(httpGet.getResponseJsonBody());
        String nextAuthor = Integer.toString(authors.get(3));

        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        jsonInputString = jsonInputString.replace("0,", nextAuthor +",");
        String first = "Gold";
        jsonInputString = jsonInputString.replace("first_sting", first);

        httpGet = new HttpMethod(method.PUT,"/api/library/author", jsonInputString);
        Assert.assertEquals(httpGet.getStatus(),200);
        String firstResponse = parceAuthor(httpGet.getResponseJsonBody());
        Assert.assertEquals( firstResponse, first);
    }
}
