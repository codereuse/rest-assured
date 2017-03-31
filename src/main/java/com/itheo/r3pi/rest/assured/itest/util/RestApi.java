package com.itheo.r3pi.rest.assured.itest.util;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

/**
 * Created by theo on 30.03.17.
 */
public class RestApi {

    private static final String REST_URL_PROPERTY_KEY = "rest.url";
    private static final String DEFAULT_SERVER = "http://jsonplaceholder.typicode.com";
    private final RestAssuredConfig restAssuredConfig = RestAssured.config().encoderConfig(encoderConfig()
            .defaultContentCharset("UTF-8"));


    public static String restApiUrl () {
        return System.getProperty(REST_URL_PROPERTY_KEY, DEFAULT_SERVER);
    }

    public Response getPostsForUserId (int id) {
        return given()
                .contentType(JSON)

                .when()
                .get(format("%s/posts?userId=%s", DEFAULT_SERVER, Integer.toString(id)));
    }

    public Response getUserById (int id) {
        return given()
                .contentType(JSON)

                .when()
                .get(format("%s/users/%s", DEFAULT_SERVER, Integer.toString(id)));
    }

    public Response postNewPost (User user) {
        return given()
                .config(restAssuredConfig)
                .contentType(JSON)
                .body(user)

                .when()
                .post(format("%s/posts", DEFAULT_SERVER));
    }
}
