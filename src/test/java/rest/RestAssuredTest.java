package rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import soap.FileRead;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
    /** work example    */
    @Test
    public void makeSureThatGoogleIsUp() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }

    @Test
    public void postmanFirstGetTest() {
        RestAssured.
                when().get("https://postman-echo.com/get?foo1=bar1&foo2=bar2").
                then().assertThat().statusCode(200).
                and().body("args.foo2", is("bar2"));
    }
    @Test
    public void UpdateRecordsWinthPut(){
        int empid = 15410;
        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1/";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "TestDate");
        requestParams.put("age", 23);
        requestParams.put("salary", 12000);
        request.body(requestParams.toString());
        Response response = request.put("/update/"+ empid);
        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void postRequest() {
        String requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": \"1\" \n}";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("foo", response.jsonPath().getString("title"));
        Assert.assertEquals("bar", response.jsonPath().getString("body"));
        Assert.assertEquals("1", response.jsonPath().getString("userId"));
        Assert.assertEquals("101", response.jsonPath().getString("id"));
    }
    /**
     * homework
     */

    @Test
    public void getAuthors() {
        given().when().get("http://localhost:8080/api/library/authors")
                .then().assertThat().statusCode(200)
                .body("authorId", hasSize(10));
    }

    @Test
    public void getAuthorId() {
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/api/library/authors");
        response.then().assertThat().statusCode(200);

        List<Integer> authorId = response.jsonPath().getList("authorId");
        List<String> first = response.jsonPath().getList("authorName.first");

        given().when().get("http://localhost:8080"+ "/api/library/author/" + authorId.get(0))
                .then().assertThat().statusCode(200)
                .body("authorName.first", is(first.get(0)));

    }

    @Test
    public void putAuthor(){
        RestAssured.baseURI = "http://localhost:8080";
        String first = "Silver";
        RequestSpecification request = RestAssured.given().contentType("application/json; utf-8");
        String jsonInputString = FileRead.readFile("src/main/resources/author.json");
        jsonInputString = jsonInputString.replace("first_sting", first);
        request.body(jsonInputString);

        request.when().put("/api/library/author")
                .then().assertThat().statusCode(200)
                .body("authorName.first", is(first) );

    }

    @Test
    public void postAuthor(){
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given().param("pagination", "false").get("/api/library/authors");
        response.then().assertThat().statusCode(200);
        List<Integer> authorId = response.jsonPath().getList("authorId");

        System.out.println(authorId);
        String first = "Silver";
        String authorIdInt = Integer.toString(authorId.get(authorId.size()-1) + 1);
        System.out.println(authorIdInt);
        String jsonInputString = FileRead.readFile("src/main/resources/author.json");

        jsonInputString = jsonInputString.replaceAll("0,",authorIdInt +  ",");
        jsonInputString = jsonInputString.replace("first_sting", first);

        RequestSpecification request = RestAssured.given().header("Content-Type", "application/json");
        request.body(jsonInputString);
        request.when().post("/api/library/author")
                .then().assertThat().statusCode(201)
                .body("authorName.first", is(first));
    }

    @Test
    public void deleteAuthors(){
        RestAssured.baseURI = "http://localhost:8080";
        Response response = RestAssured.given().param("pagination", "false").get("/api/library/authors");
        response.then().assertThat().statusCode(200);
        List<Integer> authorId = response.jsonPath().getList("authorId");
        System.out.println(authorId);
        System.out.println(authorId.get(2));

        RestAssured.given().param("forcibly", "true")
                .header("Content-type", "application/json")
                .delete("/api/library/author/" + authorId.get(2))
                .then().assertThat().statusCode(204);

        given().when().get("/api/library/author/" + authorId.get(2))
                .then().assertThat().statusCode(404);
    }
}
