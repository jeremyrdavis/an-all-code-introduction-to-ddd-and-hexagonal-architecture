package io.arrogantprogrammer.domain;


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
        RegisterAttendeeResult registerAttendeeResult = Attendee.registerAttendee(TestUtility.registerLemmyKilminster);
        assertNotNull(registerAttendeeResult.attendee());
        assertEquals(TestUtility.LemmyKilminster.email, registerAttendeeResult.attendee().email);
        assertEquals(TestUtility.LemmyKilminster.firstName, registerAttendeeResult.attendee().firstName);
        assertEquals(TestUtility.LemmyKilminster.lastName, registerAttendeeResult.attendee().lastName);
        assertEquals(TestUtility.LemmyKilminster.student, registerAttendeeResult.attendee().student);
        assertEquals(TestUtility.LemmyKilminster.impactedByLayoffs, registerAttendeeResult.attendee().impactedByLayoffs);
        assertEquals(TestUtility.LemmyKilminster.mealPreference, registerAttendeeResult.attendee().mealPreference);
        assertEquals(TestUtility.LemmyKilminster.tShirtSize, registerAttendeeResult.attendee().tShirtSize);
        assertEquals(TestUtility.LemmyKilminster.address.street, registerAttendeeResult.attendee().address.street);
        assertEquals(TestUtility.LemmyKilminster.address.street2, registerAttendeeResult.attendee().address.street2);
        assertEquals(TestUtility.LemmyKilminster.address.stateOrProvince, registerAttendeeResult.attendee().address.stateOrProvince);
        assertEquals(TestUtility.LemmyKilminster.address.city, registerAttendeeResult.attendee().address.city);
        assertEquals(TestUtility.LemmyKilminster.address.countryCode, registerAttendeeResult.attendee().address.countryCode);
        assertNotNull(registerAttendeeResult.cateringEvent());
        assertEquals(MealPreference.NONE, registerAttendeeResult.cateringEvent().mealPreference());
        assertNotNull(registerAttendeeResult.swagEvent());
        assertEquals(TShirtSize.L, registerAttendeeResult.swagEvent().tShirtSize());
        assertNotNull(registerAttendeeResult.registrationEvent());
        assertEquals(TestUtility.LemmyKilminster.email, registerAttendeeResult.registrationEvent().email());
    }

    @Test
    public void testAttendeeInfoValueObject() {
        AttendeeInfoValueObject attendeeInfoValueObject = TestUtility.LemmyKilminster.getAttendeeInfo();
        assertEquals(TestUtility.LemmyKilminster.email, attendeeInfoValueObject.email());
        assertEquals(TestUtility.LemmyKilminster.firstName, attendeeInfoValueObject.firstName());
        assertEquals(TestUtility.LemmyKilminster.lastName, attendeeInfoValueObject.lastName());
        assertEquals(TestUtility.LemmyKilminster.address.city, attendeeInfoValueObject.city());
        assertEquals(TestUtility.LemmyKilminster.address.stateOrProvince, attendeeInfoValueObject.stateOrProvince());
        assertEquals(TestUtility.LemmyKilminster.address.countryCode, attendeeInfoValueObject.countryCode());
    }
}
