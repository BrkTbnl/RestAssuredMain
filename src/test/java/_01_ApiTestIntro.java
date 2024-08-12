import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
                .log().all() //Returning body json data; log().all(): everythıng that goes and come
                .statusCode(200) // check if the status code is 200. Assertion.
        ;
    }

    @Test
    public void t3ContentType(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON) //check if the type of returned data Json. (Assertion)
        ;
    }

    @Test
    public void t4CheckCountryInResponseBody(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200) // Assertion --> Is the assertion status code 200?
                .contentType(ContentType.JSON)// Assertion --> Is the type of returned data JSON?
                .body("country", equalTo("United States"))// Assertion --> Is body's country variable equal to "United States"?


        ;
    }

    @Test
    public void t5StateInResponseBody(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places[0].state", equalTo("California"))
        ;

    }

     /* Question: Returning endpoint "http://api.zippopotam.us/tr/01000"
            * The value of "Dörtağaç Village" in any element of the place array
         * Verify that it is
         */

    @Test
    public void t6CheckHasItem(){

        given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))
        ;
    }    /* Question: Returning from "http://api.zippopotam.us/us/90210" (endpoint)
            * Verify that the string length of the place array is 1.
            */

    @Test
    public void t7CheckHasSize(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places", hasSize(1))
        ;
    }

            /* Question: Returning from "http://api.zippopotam.us/us/90210" (endpoint)
            * Verify that the string length of the place array is 1.
            */

    @Test
    public void t8ArrayHasSize(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places.size()", equalTo(1))
        ;
    }

    @Test
    public void t9Combining(){

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .body("places",hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
        ;
    }
}
