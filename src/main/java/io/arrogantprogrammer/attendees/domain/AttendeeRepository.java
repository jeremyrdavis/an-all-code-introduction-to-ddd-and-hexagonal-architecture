package io.arrogantprogrammer.attendees.domain;


import io.arrogantprogrammer.attendees.domain.Attendee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttendeeRepository implements PanacheRepository<Attendee> {
}
