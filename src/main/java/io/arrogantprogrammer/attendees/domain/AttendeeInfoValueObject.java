package io.arrogantprogrammer.attendees.domain;

public record AttendeeInfoValueObject(
        String email,
        String firstName,
        String lastName,
        String city,
        String stateOrProvince,
        String countryCode
) {
}
