package io.arrogantprogrammer.domain;

public class TestUtility {

    public static Attendee LemmyKilminster = new Attendee(
            "lemmy@motorhead.com",
            "Lemmy",
            "Kilminster",
            false,
            false,
            TShirtSize.L,
            MealPreference.NONE,
            new Address(
            "6300 Forest Lawn Dr",
            "Courts of Remembrance section, Columbarium of Sacred Trust, Map #ELB0, Elevation B (facing west wall), Outdoor Garden Niche 66400",
            "Los Angeles",
            "CA",
            "90068",
            "USA")
    );

    public static RegisterAttendeeCommand registerLemmyKilminster = new RegisterAttendeeCommand(
            "lemmy@motorhead.com",
            "Lemmy",
            "Kilminster",
            false,
            false,
            TShirtSize.L,
            MealPreference.NONE,
"6300 Forest Lawn Dr",
            "Courts of Remembrance section, Columbarium of Sacred Trust, Map #ELB0, Elevation B (facing west wall), Outdoor Garden Niche 66400",
            "Los Angeles",
            "CA",
            "90068",
            "USA",
            "http://www.motorhead.com");

}
