package io.arrogantprogrammer.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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