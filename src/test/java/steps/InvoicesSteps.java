package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class InvoicesSteps {

    RequestSpecification request;
    Response response;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjE5MjMwNDMsImlhdCI6MTcxOTMzMTA0MywidXNlcm5hbWUiOiJlbWlyXzc3Nzc3QG1haWwucnUifQ.Lq1N6zyyy9UY5wW4SXM3bQGwwcl9f8dzz_eAxsD6FJkqrQYYFjbHB5PYqgHrpsIyqUIUgJ1hFfWA7Uj2p5GJcQ";
    JSONObject requestBody = new JSONObject();



}
