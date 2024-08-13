import models.Location;
import models.Place;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class _04_ApiTestPOJO {

    @Test
    public void t1extractJsonAll_POJO() {

        Location locationObj =
                given()

                        .when()
                        .get("http://api.zippopotam.us/tr/01000")

                        .then()
                        .log().body()
                        .extract().body().as(Location.class)
                                            // as type of location
                ;

        System.out.println("locationObj = " + locationObj.getCountry());
        System.out.println("locationObj = " + locationObj.getPlaces());

        for(Place p : locationObj.getPlaces()){
            System.out.println("p = " + p);
        }

    }
}