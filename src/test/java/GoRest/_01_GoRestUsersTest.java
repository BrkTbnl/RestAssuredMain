package GoRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _01_GoRestUsersTest {

    RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {
        baseURI = "https://gorest.co.in/public/v2/users";

        reqSpec = new RequestSpecBuilder()

                .addHeader("Authorization", "Bearer b1f7c7789f92ce78dc739afe1bdb192eb27b9c97b328ae98409e0b12639e4340")
                .setContentType(ContentType.JSON)
                .build();
    }

    Faker randomFaker = new Faker();
    int userId = 0;

    @Test(enabled = false)
    public void createUserJson() {

        String fullName = randomFaker.name().fullName();
        String email = randomFaker.internet().emailAddress();

        userId =
                given() // body, token, contentType
                        .header("Authorization", "Bearer 6e2a70107d231adae49cbeb2d30685ac536fef76a7401004e39ac3f5a3b2088a")
                        .body("{\"name\":\"" + fullName + "\", \"gender\":\"male\", \"email\":\"" + email + "\",  \"status\":\"active\"}")
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");

        System.out.println("userId = " + userId);
    }

    @Test
    public void createUserMap() {

        String fullName = randomFaker.name().fullName();
        String email = randomFaker.internet().emailAddress();

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", fullName);
        newUser.put("gender", "male");
        newUser.put("email", email);
        newUser.put("status", "active");

        userId =
                given() // body, token, contentType
                        .spec(reqSpec)
                        .body(newUser)

                        .when()
                        .post()

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");

        System.out.println("userId = " + userId);
    }

    @Test(enabled = false)
    public void createUserClass() {

        String fullName = randomFaker.name().fullName();
        String email = randomFaker.internet().emailAddress();

        User newUser = new User();
        newUser.name = fullName;
        newUser.gender = "male";
        newUser.email = email;
        newUser.status = "active";


        userId =
                given() // body, token, contentType
                        .header("Authorization", "Bearer 6e2a70107d231adae49cbeb2d30685ac536fef76a7401004e39ac3f5a3b2088a")
                        .body(newUser)
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");

        System.out.println("userId = " + userId);
    }

    @Test(dependsOnMethods = "createUserMap")
    public void getUserById() {

        given()
                .spec(reqSpec)
                .when()
                .get("/" + userId)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userId))

        ;
    }

    @Test(dependsOnMethods = "getUserById")
    public void updateUser() {
        Map<String, String> updateUser = new HashMap<>();
        updateUser.put("name", "bt");

        given()
                .spec(reqSpec)
                .body(updateUser)

                .when()
                .put("/" + userId)

                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo("bt"))
        ;

    }

    @Test(dependsOnMethods = "updateUser")
    public void deleteUser() {

        given()
                .spec(reqSpec)

                .when()
                .delete("/"+userId)

                .then()
                .log().all()
                .statusCode(204)
        ;
    }

    // Perform the user delete negative API test
    @Test(dependsOnMethods = "deleteUser")
    public void deleteUserNegative() {

        given()
                .spec(reqSpec)

                .when()
                .delete("" + userId)

                .then()
                //.log().all()
                .statusCode(404)
        ;
    }


}
