package ceo.dog;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;
import org.testng.annotations.Test;

import static ceo.dog.application.Urls.DOG_API;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RandomImageTest {

    // The Dog API project only accepts jpg files per https://github.com/jigsawpieces/dog-api-images/?tab=readme-ov-file#submission-guide
    @Test(groups ={"smoke", "regression"})
    public void testStatusCodeAndFileType() {
        given()
                .when()
                .get(Urls.DOG_API + Endpoints.RANDOM_IMAGE)
                .then()
                .statusCode(200)
                .body("message", endsWith(".jpg"));
    }
}
