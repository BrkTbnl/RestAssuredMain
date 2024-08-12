import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _02_ApiTestSpec {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;


    @BeforeTest
    public void setup(){

        baseURI = "https://gorest.co.in/public/v1";

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI) //log().uri()
                .build()
        ;

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200) //status code check
                .log(LogDetail.BODY) // .log().body()
                .build()
        ;
    }

    @Test
    public void t13BaseUriWithParameters(){
        given()
                .param("page", 1)
                .spec(requestSpec)

                .when()
                .get("/users")

                .then()
                .spec(responseSpec)
        ;
    }
}
