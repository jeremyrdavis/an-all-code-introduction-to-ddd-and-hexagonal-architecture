package io.arrogantprogrammer.attendees.domain;

import io.arrogantprogrammer.attendees.domain.Attendee;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Address extends PanacheEntity {

    @OneToOne(mappedBy = "address")
    Attendee attendee;

    String street;

    String street2;

    String city;

    String postalCode;

    String stateOrProvince;

    String countryCode;

    public Address() {
    }

    Address(String street, String street2, String city, String postalCode, String stateOrProvince, String countryCode) {
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.postalCode = postalCode;
        this.stateOrProvince = stateOrProvince;
        this.countryCode = countryCode;
    }
}