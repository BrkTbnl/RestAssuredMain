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
                        .extract().path("places[0].state");

        System.out.println("State: " + stateName);
        Assert.assertEquals(stateName, "California");

    }

    /* Question: The first element of the place array returned from the endpoint
     * "http://api.zippopotam.us/us/90210"
     * verify that the place name value is "Beverly Hills" with testNG Assertion
     */

    @Test
    public void t03extractingJSONPath3() {

        String placeName =
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .log().body()
                        .extract().path("places[0].'place name'");
        System.out.println("placeName = " + placeName);
        Assert.assertEquals(placeName, "Beverly Hills");
    }

    /* Question: Verify with testNG that the limit information returned from the
     * "https://gorest.co.in/public/v1/users" endpoint is 10.
     */

    @Test
    public void t04extractingJSONPath4() {


        int limit =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        Assert.assertEquals(limit, 10, "limit is not 10!");

    }
}
