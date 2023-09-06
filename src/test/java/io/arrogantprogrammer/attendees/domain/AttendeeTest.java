package io.arrogantprogrammer.attendees.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttendeeTest {

    @Test
    public void testPriceCaluculationForStudent() {

        Attendee studentAttendee = new Attendee();
        studentAttendee.student = true;
        assertEquals(585.0, studentAttendee.calculatePrice(650.0));
    }

    @Test
    public void testPriceCalculationForImpactedByLayoffs() {
        Attendee attendee = new Attendee();
        attendee.impactedByLayoffs = true;
        assertEquals(325.00, attendee.calculatePrice(650.0));
    }

    @Test
    public void testPriceCalculationWithNoDiscount() {
        Attendee attendee = new Attendee();
        assertEquals(650.0, attendee.calculatePrice(650.0));
    }

    @Test
    public void testCreateFromRegisterAttendeeCommand() {
        RegisterAttendeeResult registerAttendeeResult = Attendee.registerAttendee(AttendeeTestUtility.registerLemmyKilminster);
        assertNotNull(registerAttendeeResult.attendee());
        assertEquals(AttendeeTestUtility.LemmyKilminster.email, registerAttendeeResult.attendee().email);
        assertEquals(AttendeeTestUtility.LemmyKilminster.firstName, registerAttendeeResult.attendee().firstName);
        assertEquals(AttendeeTestUtility.LemmyKilminster.lastName, registerAttendeeResult.attendee().lastName);
        assertEquals(AttendeeTestUtility.LemmyKilminster.student, registerAttendeeResult.attendee().student);
        assertEquals(AttendeeTestUtility.LemmyKilminster.impactedByLayoffs, registerAttendeeResult.attendee().impactedByLayoffs);
        assertEquals(AttendeeTestUtility.LemmyKilminster.mealPreference, registerAttendeeResult.attendee().mealPreference);
        assertEquals(AttendeeTestUtility.LemmyKilminster.tShirtSize, registerAttendeeResult.attendee().tShirtSize);
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.street, registerAttendeeResult.attendee().address.street);
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.street2, registerAttendeeResult.attendee().address.street2);
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.stateOrProvince, registerAttendeeResult.attendee().address.stateOrProvince);
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.city, registerAttendeeResult.attendee().address.city);
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.countryCode, registerAttendeeResult.attendee().address.countryCode);
        assertNotNull(registerAttendeeResult.cateringEvent());
        Assertions.assertEquals(MealPreference.NONE, registerAttendeeResult.cateringEvent().mealPreference());
        assertNotNull(registerAttendeeResult.swagEvent());
        Assertions.assertEquals(TShirtSize.L, registerAttendeeResult.swagEvent().tShirtSize());
        assertNotNull(registerAttendeeResult.registrationEvent());
        assertEquals(AttendeeTestUtility.LemmyKilminster.email, registerAttendeeResult.registrationEvent().email());
    }

    @Test
    public void testAttendeeInfoValueObject() {
        AttendeeInfoValueObject attendeeInfoValueObject = AttendeeTestUtility.LemmyKilminster.getAttendeeInfo();
        assertEquals(AttendeeTestUtility.LemmyKilminster.email, attendeeInfoValueObject.email());
        assertEquals(AttendeeTestUtility.LemmyKilminster.firstName, attendeeInfoValueObject.firstName());
        assertEquals(AttendeeTestUtility.LemmyKilminster.lastName, attendeeInfoValueObject.lastName());
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.city, attendeeInfoValueObject.city());
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.stateOrProvince, attendeeInfoValueObject.stateOrProvince());
        assertEquals(AttendeeTestUtility.LemmyKilminster.address.countryCode, attendeeInfoValueObject.countryCode());
    }
}
