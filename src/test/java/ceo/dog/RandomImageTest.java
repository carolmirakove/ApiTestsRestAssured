package ceo.dog;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RandomImageTest {

    public static final String IMAGE_RANDOM = "https://dog.ceo/api/breeds/image/random";

    @Test(groups ={"smoke", "regression"})
    public void testStatusCode() {
        given()
                .when()
                .get(IMAGE_RANDOM)
                .then()
                .statusCode(200);
    }

    @Test(groups ="regression")
    public void testFileType() {
        given()
                .when()
                .get(IMAGE_RANDOM)
                .then()
                .body("message", endsWith(".jpg"));
    }
}
