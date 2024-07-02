import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.Seller;
import pojo.Tag;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

public class IntroToSerialization {
    public static void main(String[] args) {
        /*
        Create a token
         */
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzAsImlhdCI6MTcxNTk1NjMzMCwidXNlcm5hbWUiOiJlbWlyXzc3Nzc3QG1haWwucnUifQ.ftuqbs_AUYJ4uPRzpau7FAJVVAiTydUQ87ur30Vmk64qrXRbO_8ZrPVTFtL55XYle-BKdoX_mV-FIUCTni09uw";

        /*
        1. create a pojo object
         */
        Tag tag = new Tag();
        tag.setName_tag("melon");
        tag.setDescription("tomato tag");

        /*
        2. serialize pojo object to json object
         */

        Gson gson = new Gson();
        String requestBodyInJson = gson.toJson(tag);
        System.out.println(requestBodyInJson);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBodyInJson)
                .when()
                .post("/myaccount/tags");
        System.out.println(response.statusCode());
//        System.out.println(response.prettyPrint());
        response.prettyPrint();

        /*
        3. Deserialize json to java
         */
        String responseInJson = response.asString();
        gson = new Gson();
        Tag tagResponse = gson.fromJson(responseInJson, Tag.class);
        System.out.println(tagResponse.getMessage());
        System.out.println(Arrays.toString(tagResponse.getDetails()));

//        Assert.assertTrue(tagResponse.getName_tag().equals(tag.getName_tag()));

        /*
        test create seller endpoint
         */

        // 1. Create pojo object
        Faker faker = new Faker();

        Seller seller = new Seller();
        seller.setCompany_name(faker.company().name());
        seller.setSeller_name(faker.name().fullName());
        seller.setEmail(faker.internet().emailAddress());
        seller.setPhone_number(faker.phoneNumber().cellPhone());
        seller.setAddress(faker.address().streetAddress());

        // 2. serialize to json

        gson = new Gson();
        String requestInJson = gson.toJson(seller, Seller.class);

        // 3. send the request
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .body(requestInJson)
                .when()
                .post("/sellers");

        /*
        deserialize from json to java
         */
        responseInJson = response.asString();
        gson = new Gson();
        Seller responseSeller = gson.fromJson(responseInJson, Seller.class);
        response.prettyPrint();
        System.out.println(response.statusCode());

        /*
        verify status code
         */
        Assert.assertEquals(response.statusCode(), 201);

        /*
        verify response body
         */

        Assert.assertTrue(responseSeller.getSeller_id() != 0);
        Assert.assertEquals(responseSeller.getCompany_name(), seller.getCompany_name());
    }
}
