package ceo.dog.application.responseParsers;

import ceo.dog.application.Endpoints;
import ceo.dog.application.Urls;

import static io.restassured.RestAssured.given;

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

    public static String getBreedWithSubBreedsName() {
        LinkedHashMap<String, ArrayList<String>> breeds = getBreedsAndSubBreeds();

        for (Map.Entry<String, ArrayList<String>> breed : breeds.entrySet()) {
            String key = breed.getKey();
            ArrayList<String> value = breed.getValue();

            if (!value.isEmpty()) {
                return key;
            }
        }
        return null;
    }

    public static String getBreedWithoutSubBreedsName() {
        LinkedHashMap<String, ArrayList<String>> breeds = getBreedsAndSubBreeds();

        for (Map.Entry<String, ArrayList<String>> breed : breeds.entrySet()) {
            String key = breed.getKey();
            ArrayList<String> value = breed.getValue();

            if (value.isEmpty()) {
                return key;
            }
        }
        return null;
    }

    private static Set<String> getBreedNames() {
        LinkedHashMap<String, ArrayList<String>> message = getBreedsAndSubBreeds();

        return message.keySet();
    }

    private static LinkedHashMap<String, ArrayList<String>> getBreedsAndSubBreeds() {
        /* The response body message field contains a linked hash map of breeds (key)
        and their sub-breeds (values). Some api endpoints take a breed value as a param,
        e.g., list all sub-breeds for a given breed. The set returned here serves as a
        helper by which a method can obtain a single breed name to pass to an endpoint.*/
        LinkedHashMap<String, ArrayList<String>> message;
        message = given()
                .when()
                .get(Urls.DOG_API + Endpoints.ALL_BREEDS)
                .then()
                .statusCode(200)
                .extract()
                .path("message");

        return message;
    }
}
