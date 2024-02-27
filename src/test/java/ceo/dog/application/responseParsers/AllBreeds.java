package ceo.dog.application.responseParsers;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Array;
import java.util.*;

public class AllBreeds {
    public static String getRandomBreedName() {
        Set<String> breedNames = getBreedNames();
        if (breedNames.isEmpty()) {
            throw new IllegalArgumentException("The set of breed names is empty.");
        }
        int randomIndex = new Random().nextInt(breedNames.size());
        int i = 0;
        for (String breedName : breedNames) {
            if (i == randomIndex) {
                return breedName;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong while picking a random element.");
    }

    private static Set<String> getBreedNames() {
        RestAssured.defaultParser = Parser.JSON;

        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, Urls.DOG_API + Endpoints.ALL_BREEDS);

        Assert.assertEquals(response.getStatusCode(), 200);

        /* The response body message field contains a linked hash map of breeds (key)
        and their sub-breeds (values). Some api endpoints take a breed value as a param,
        e.g., list all sub-breeds for a given breed. The set returned here serves as a
        helper by which a method can obtain a single breed name to pass to an endpoint.*/
        LinkedHashMap<String, Array> message =
                given()
                        .when()
                        .get(Urls.DOG_API + Endpoints.ALL_BREEDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("message");

        return message.keySet();
    }
}
