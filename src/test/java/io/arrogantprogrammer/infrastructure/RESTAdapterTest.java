package io.arrogantprogrammer.infrastructure;

import io.arrogantprogrammer.domain.TestUtility;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@QuarkusTest
public class RESTAdapterTest {

    @Test
    public void testRegisteringAttendee() {

        given()
            .with()
            .header("Content-Type", ContentType.JSON)
            .body(TestUtility.LemmyKilminster)
            .when()
            .post("/attendees")
            .then().statusCode(RestResponse.StatusCode.ACCEPTED);
    }


}
