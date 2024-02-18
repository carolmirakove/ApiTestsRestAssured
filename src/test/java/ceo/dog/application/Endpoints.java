package ceo.dog.application;

public class Endpoints {
    public static final String ALL_BREEDS = "breeds/list/all";
    public static final String RANDOM_IMAGE = "breeds/image/random";

    public static String allImagesByBreed(String breedName) {
        return "breed/" + breedName + "/images";
    }

    public static String randomImageByBreed(String breedName) {
        return "breed/" + breedName + "/images/random";
    }

    public static String subBreedsByBreed(String breedName) {
        return "breed/" + breedName + "/list";
    }
}
