import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _01_ApiTestIntro {

    @Test
    public void Test1() {

        given()
                // Preparatory procedures codes
                .when()
                // Endpoint (url), giving the method and sending the request


                .then()
                // Assertion, test, data operations
        ;

    }

    @Test
    public void t2StatusCode(){

        given()
                // Preparation section is empty

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() //Returning body json data; log().all(): everythıng that goes and come
                .statusCode(200) // check if the status code is 200. Assertion.

        ;

    }

}
