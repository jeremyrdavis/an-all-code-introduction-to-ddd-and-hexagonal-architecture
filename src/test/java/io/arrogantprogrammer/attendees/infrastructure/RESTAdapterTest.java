package io.arrogantprogrammer.attendees.infrastructure;

import io.arrogantprogrammer.attendees.domain.AttendeeInfoValueObject;
import io.arrogantprogrammer.attendees.domain.AttendeeService;
import io.arrogantprogrammer.attendees.domain.AttendeeTestUtility;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
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
            .body(AttendeeTestUtility.LemmyKilminster)
            .when()
            .post("/attendees")
            .then().statusCode(RestResponse.StatusCode.ACCEPTED);
    }

    @Test
    public void testRegistration() {

        attendeeService = Mockito.mock(AttendeeService.class);

        List<AttendeeInfoValueObject> attendeeInfoValueObjectList = new ArrayList<>(){{
            add(AttendeeTestUtility.LemmyKilminster.getAttendeeInfo());
        }};
        Mockito.when(attendeeService.listAll()).thenReturn(attendeeInfoValueObjectList);
        QuarkusMock.installMockForType(attendeeService, AttendeeService.class);

        when()
                .get("/attendees")
                .then()
                .statusCode(RestResponse.StatusCode.OK)
                .assertThat()
                .body("email", hasItem(AttendeeTestUtility.LemmyKilminster.getAttendeeInfo().email()));
    }


}
