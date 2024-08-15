package GoRest;

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

    @Test
    public void createUser() {
        int userId =
        given() // body, token, contentType
                .header("Authorization", "Bearer 6e2a70107d231adae49cbeb2d30685ac536fef76a7401004e39ac3f5a3b2088a")
                .body("{\"name\":\"bt1\", \"gender\":\"male\", \"email\":\"nomail1@allowed.here\",  \"status\":\"active\"}")
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
