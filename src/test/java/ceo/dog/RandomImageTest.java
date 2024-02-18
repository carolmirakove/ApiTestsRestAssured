package ceo.dog;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RandomImageTest {

    // TODO replace IMAGE_RANDOM constant with test environment constants for base urls and resource constants for API endpoints
    public static final String IMAGE_RANDOM = "https://dog.ceo/api/breeds/image/random";

    // The Dog API project only accepts jpg files per https://github.com/jigsawpieces/dog-api-images/?tab=readme-ov-file#submission-guide
    @Test(groups ={"smoke", "regression"})
    public void testStatusCodeAndFileType() {
        given()
                .when()
                .get(IMAGE_RANDOM)
                .then()
                .statusCode(200)
                .body("message", endsWith(".jpg"));
    }
}
