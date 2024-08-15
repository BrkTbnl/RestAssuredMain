import org.testng.annotations.Test;
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

    /*
     Print only the "data" part returned from the https://gorest.co.in/public/v1/users endpoint using POJO conversion.
    */





}
