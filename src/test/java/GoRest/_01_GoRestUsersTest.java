package GoRest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _01_GoRestUsersTest {
    /*
    https://gorest.co.in/public/v2/users

    {"name":"{{$randomFullName}}",
    "gender":"male",
    "email":"{{$randomEmail}}",
    "status":"active"}
    */

    Faker randomFaker = new Faker();
    int userId = 0;

    @Test
    public void createUser() {

        String fullName = randomFaker.name().fullName();
        String email = randomFaker.internet().emailAddress();

        userId =
        given() // body, token, contentType
                .header("Authorization", "Bearer 6e2a70107d231adae49cbeb2d30685ac536fef76a7401004e39ac3f5a3b2088a")
                .body("{\"name\":\""+fullName+"\", \"gender\":\"male\", \"email\":\""+email+"\",  \"status\":\"active\"}")
                .contentType(ContentType.JSON)

                .when()
                .post("https://gorest.co.in/public/v2/users")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");

        System.out.println("userId = " + userId);

        ;
    }
}
