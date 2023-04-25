package io.arrogantprogrammer.domain;


import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttendeeRepository implements PanacheRepository<Attendee> {
}
