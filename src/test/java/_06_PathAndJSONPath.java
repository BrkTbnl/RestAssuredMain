import io.restassured.response.Response;
import models.Place;
import models.User;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class _06_PathAndJSONPath {

    @Test
    public void extractingPath() {

        String postCode =

            given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .extract().path("'post code'");

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJSONPath() {

        int postCode =

            given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .extract().jsonPath().getInt("'post code'");
        //Allows class conversion and type
        // conversion and gives the data in the format we want.

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void getZipCode() {
        Response response =
                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().response();

//        Location locationPathAs = response.as(Location.class);
//          instead of doin this

        List<Place> places = response.jsonPath().getList("places", Place.class);
        System.out.println("places = " + places);

    }

    /*
     Print only the "data" part returned from the https://gorest.co.in/public/v1/users endpoint using POJO conversion.
    */

    @Test
    public void getUserData() {

        List<User> userData =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")


                        .then()
//              .log().body()
                        .extract().jsonPath().getList("data", User.class);

        //System.out.println("userData.get(0).getEmail() = " + userData.get(0).getEmail());

        for (User user : userData) {
            System.out.println("user = " + user);
        }
    }





}
