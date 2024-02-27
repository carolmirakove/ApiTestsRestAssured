package ceo.dog;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;
import ceo.dog.application.responseParsers.AllBreeds;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ImagesByBreedTest {

    @Test(groups ={"smoke", "regression"})
    public void testStatusBreedAndFileType() {
        String breedName = AllBreeds.getRandomBreedName();

        ArrayList<String> message = given()
                .when()
                .get(Urls.DOG_API + Endpoints.allImagesByBreed(breedName))
                .then()
                .statusCode(200)
                .extract()
                .path("message");

        if (message.isEmpty()) {
            throw new IllegalArgumentException("The response body message is empty.");
        }

        /* Get the first item from the list and assert it is a jpg file.
        (the Dog API project only accepts jpg files). */
        assertThat(message.get(0), endsWith(".jpg"));
        assertThat(message.get(0), containsString(breedName));
    }
}
