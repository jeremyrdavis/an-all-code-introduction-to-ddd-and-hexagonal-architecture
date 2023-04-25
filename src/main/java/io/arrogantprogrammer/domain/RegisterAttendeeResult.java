package io.arrogantprogrammer.domain;

public record RegisterAttendeeResult(Attendee attendee, RegistrationEvent registrationEvent,
                                     CateringEvent cateringEvent, SwagEvent swagEvent) {
}
