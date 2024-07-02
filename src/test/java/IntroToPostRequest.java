import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToPostRequest {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMzAsImlhdCI6MTcxNTk1NjMzMCwidXNlcm5hbWUiOiJlbWlyXzc3Nzc3QG1haWwucnUifQ.ftuqbs_AUYJ4uPRzpau7FAJVVAiTydUQ87ur30Vmk64qrXRbO_8ZrPVTFtL55XYle-BKdoX_mV-FIUCTni09uw";

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_title", "lagm2222an");
        requestBody.put("product_price", 32);
        requestBody.put("service_type_id", 1);
        requestBody.put("category_id", 1027);
        requestBody.put("product_description", "Italian");
        requestBody.put("date_of_payment", "2024-05-20");
        requestBody.put("remind_before_day", 2);
        requestBody.put("do_remind_every_month", "REPEAT_EVERY_MONTH");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBody.toString())
                .when()
                .post("/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("lagm2222an"))
                .body("product_price", equalTo(32.0F));
    }
}
