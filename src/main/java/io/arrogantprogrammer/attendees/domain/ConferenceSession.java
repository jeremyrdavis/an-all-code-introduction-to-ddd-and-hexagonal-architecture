package io.arrogantprogrammer.attendees.domain;

import io.arrogantprogrammer.attendees.domain.Attendee;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "conference_session")
public class ConferenceSession extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    String title;

    String presenter;

    Instant startTime;

    public ConferenceSession() {
    }
}