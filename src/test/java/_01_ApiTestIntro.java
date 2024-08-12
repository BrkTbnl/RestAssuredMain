import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ApiTestIntro {

    @Test
    public void Test01() {

        given()
                // Preparatory procedures codes
                .when()
                // Endpoint (url), giving the method and sending the request


                .then()
                // Assertion, test, data operations
        ;

    }

    @Test
    public void t02StatusCode(){

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
    public void t03ContentType(){

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
    public void t04CheckCountryInResponseBody(){

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
    public void t05StateInResponseBody(){

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
    public void t06CheckHasItem(){

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
    public void t07CheckHasSize(){

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
    public void t08ArrayHasSize(){

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
    public void t09Combining(){

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

    @Test
    public void t10PathParam(){

        given()
                .pathParam("country","us")
                .pathParam("Code",90210)
                .log().uri() //shows completed API after using parameters

                .when()
                .get("http://api.zippopotam.us/{country}/{Code}")

                .then()
                .log().body()
                ;
    }

    @Test
    public void t11QueryParam() {

        // https://gorest.co.in/public/v1/users?page=1

        given()
                .param("page", 1) // It is added to the link as ?page=1
                // It can also be used with queryParam
                //.queryParam("page", 1)
                .log().uri()

                .when()
                .get("https://gorest.co.in/public/v1/users") // ?page=1
                // .get("https://gorest.co.in/public/v1/users?page=1")

                .then()
                .statusCode(200)
                .log().body()
        ;
    }

    /*
    https://gorest.co.in/public/v1/users?page=3
    When you call pages 1 to 10 from the link, the returned page values in the response
    Check whether it is the same as the called page number.
    */

    @Test
    public void t12QueryParam2() {
        for (int i = 1; i <=10 ; i++) {

            given()
                    .param("page", i)
                    .log().uri()

                    .when()
                    .get("https://gorest.co.in/public/v1/users")

                    .then()
                    .statusCode(200)
                    .log().body()
                    .body("meta.pagination.page", equalTo(i))

            ;
        }
    }

}
