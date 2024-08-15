import io.restassured.http.ContentType;
import models.Location;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import models.Todo;

public class _05_Todo {
    /*
     * Task 1
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type JSON
     * expect title in response body to be "quis ut nam facilis et officia qui"
     */


    @Test
    public void task1() {
        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("quis ut nam facilis et officia qui"))
        ;
    }

    /*
     * Task 2
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type JSON
     * a) expect response completed status to be false(hamcrest)
     * b) extract completed field and testNG assertion(testNG)
     */
    @Test
    public void task2a() {

        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("completed", equalTo(false))
        ;
    }

    @Test
    public void task2b() {

        boolean completed =
                given()

                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos/2")

                        .then()
                        .log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().body().path("completed");
        ;

        Assert.assertFalse(completed);
//       or you can say  Assert.assertEquals(completed, false);

    }



    /*
     * Task 3
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * Converting Into POJO
     */

    @Test
    public void task3() {
        Todo todoObj =
            given()
    
                    .when()
                    .get("https://jsonplaceholder.typicode.com/todos/2")
                    
                    .then()
                    .statusCode(200)
                    .log().body()
                    .extract().body().as(Todo.class)
            ;


        System.out.println(todoObj.toString());

    }


}
