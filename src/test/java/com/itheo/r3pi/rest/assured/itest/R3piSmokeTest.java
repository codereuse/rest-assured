package com.itheo.r3pi.rest.assured.itest;

import com.itheo.r3pi.rest.assured.itest.util.RestApi;
import com.itheo.r3pi.rest.assured.itest.util.User;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.itheo.r3pi.rest.assured.itest.util.EmailValidator.isValidEmailAddress;
import static com.itheo.r3pi.rest.assured.itest.util.RandomIntegerGenerator.getRandomIntegerInRange;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Created by theo on 30.03.17.
 */
public class R3piSmokeTest {

    private RestApi restAPI = new RestApi();

    @BeforeClass
    public static void setup () {
        RestAssured.baseURI = RestApi.restApiUrl();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testStatusOk () {
        given().when().get("/users")
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void testInvalidUser () {
        given().when().get("/users/11")
                .then()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    public void testValidEmailAddressForRandomUser () {
        int randomUser = getRandomIntegerInRange(1,10);

        String email = restAPI.getUserById(randomUser)
                .then()
                .extract().path("email");

        // another way to extract
        // String email = restAPI.getUserById(randomUser).jsonPath().get("email").toString();
        System.out.println("User number " + randomUser + " has email address: " + email);

        assertThat(isValidEmailAddress(email)).isTrue();
    }

    @Test
    public void testValidPostsForRandomUser () {
        int randomUser = getRandomIntegerInRange(1,10);

        restAPI.getPostsForUserId(randomUser)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("jsonplaceholder-posts-schema.json"));

        //i.e. Accessing comments if needed
        //List<String> commentsTitles = restAPI.getPostsForUserId(randomUser).then().extract().path("title");
    }

    @Test
    public void testPostMethod () {
        User user = new User("foo", "bar", 1);

        restAPI.postNewPost(user)
                .then()
                .statusCode(SC_CREATED);
    }
}
