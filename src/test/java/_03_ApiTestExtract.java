import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _03_ApiTestExtract {

    @Test
    public void t01extractingJSONPath() {

        String countryName =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("country") // Make PATH country value EXTRACT
                ;

        System.out.println("Country: " + countryName);
        Assert.assertEquals(countryName, "United States");// Is the received value equal to this?

    }

    /* Question: The first element of the place array returned from the endpoint
     "http://api.zippopotam.us/us/90210"
     * verify that the state value is "California" with testNG Assertion
     */

    @Test
    public void t02extractingJSONPath2() {

        String stateName =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("places[0].state")
                ;

        System.out.println("State: " + stateName);
        Assert.assertEquals(stateName, "California");

    }
}
