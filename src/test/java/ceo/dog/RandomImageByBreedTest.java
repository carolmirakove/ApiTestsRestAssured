package ceo.dog;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;
import ceo.dog.application.responseParsers.AllBreeds;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;

public class RandomImageByBreedTest {
    @Test(groups ={"smoke", "regression"})
    public void testStatusBreedAndFileType() {
        String breedName = AllBreeds.getRandomBreedName();

        given()
                .when()
                .get(Urls.DOG_API + Endpoints.randomImageByBreed(breedName))
                .then()
                .statusCode(200)
                .body("message", endsWith(".jpg")) /* The Dog API project only accepts jpg files */
                .body("message", containsString(breedName));
    }
}
