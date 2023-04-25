package io.arrogantprogrammer.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
}