package ceo.dog;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;
import ceo.dog.application.responseParsers.AllBreeds;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ListSubBreedsTest {
    @Test(groups ={"smoke", "regression"})
    public void testSubBreedStatusAndContent() {
        String breedName = AllBreeds.getBreedWithSubBreedsName();

        ArrayList<String> message = given()
                .when()
                .get(Urls.DOG_API + Endpoints.subBreedsByBreed(breedName))
                .then()
                .statusCode(200)
                .extract()
                .path("message");

        assertThat(message, not(is(empty())));
        assertThat(message.get(0), not(is(emptyString())));
    }
}
