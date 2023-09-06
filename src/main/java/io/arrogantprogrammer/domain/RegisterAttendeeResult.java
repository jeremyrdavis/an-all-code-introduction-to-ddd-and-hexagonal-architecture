package io.arrogantprogrammer.domain;

import io.arrogantprogrammer.attendees.domain.Attendee;

public record RegisterAttendeeResult(Attendee attendee, RegistrationEvent registrationEvent,
                                     CateringEvent cateringEvent, SwagEvent swagEvent) {
}
