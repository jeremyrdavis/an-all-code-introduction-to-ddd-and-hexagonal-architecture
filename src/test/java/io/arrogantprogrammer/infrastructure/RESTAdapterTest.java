package io.arrogantprogrammer.infrastructure;

import io.arrogantprogrammer.domain.AttendeeInfoValueObject;
import io.arrogantprogrammer.domain.AttendeeService;
import io.arrogantprogrammer.domain.TestUtility;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest //@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RESTAdapterTest {

    @Inject
    AttendeeService attendeeService;

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

    @Test
    public void testRegistration() {

        attendeeService = Mockito.mock(AttendeeService.class);

        List<AttendeeInfoValueObject> attendeeInfoValueObjectList = new ArrayList<>(){{
            add(TestUtility.LemmyKilminster.getAttendeeInfo());
        }};
        Mockito.when(attendeeService.listAll()).thenReturn(attendeeInfoValueObjectList);
        QuarkusMock.installMockForType(attendeeService, AttendeeService.class);

        when()
                .get("/attendees")
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .assertThat()
                .body("email", hasItem(TestUtility.LemmyKilminster.getAttendeeInfo().email()));
    }


}
