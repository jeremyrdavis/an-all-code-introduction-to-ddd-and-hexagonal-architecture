package io.arrogantprogrammer.attendees.domain;

public record RegisterAttendeeResult(Attendee attendee, RegistrationEvent registrationEvent,
                                     CateringEvent cateringEvent, SwagEvent swagEvent) {
}
