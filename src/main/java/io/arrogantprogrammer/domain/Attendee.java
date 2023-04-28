package io.arrogantprogrammer.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Attendee extends PanacheEntity {

    String email;

    String firstName;

    String lastName;

    boolean student;

    boolean impactedByLayoffs;

    @Enumerated(EnumType.STRING)
    TShirtSize tShirtSize;

    @Enumerated(EnumType.STRING)
    MealPreference mealPreference;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "attendee")
    List<ConferenceSession> conferenceSessionList = new java.util.ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;

    public Double calculatePrice(Double basePrice) {
        if (student) {
            return basePrice - (basePrice * 0.10);
        } else if (impactedByLayoffs) {
            return basePrice - (basePrice * 0.50);
        }else{
            return basePrice;
        }
    }

    public AttendeeInfoValueObject getAttendeeInfo() {
        return new AttendeeInfoValueObject(
                this.email,
                this.firstName,
                this.lastName,
                this.address.city,
                this.address.stateOrProvince,
                this.address.countryCode
        );
    }

    static RegisterAttendeeResult registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        Attendee attendee = new Attendee(
                registerAttendeeCommand.email(),
                registerAttendeeCommand.firstName(),
                registerAttendeeCommand.lastName(),
                registerAttendeeCommand.student(),
                registerAttendeeCommand.impactedByLayoffs(),
                registerAttendeeCommand.tShirtSize(),
                registerAttendeeCommand.mealPreference(),
                new Address(
                        registerAttendeeCommand.streetAddress(),
                        registerAttendeeCommand.streetAddress2(),
                        registerAttendeeCommand.city(),
                        registerAttendeeCommand.postalCode(),
                        registerAttendeeCommand.stateOrProvince(),
                        registerAttendeeCommand.countryCode()
                )
        );

        RegistrationEvent registrationEvent = new RegistrationEvent(attendee.email);
        CateringEvent cateringEvent = new CateringEvent(attendee.mealPreference);
        SwagEvent swagEvent = new SwagEvent(attendee.tShirtSize);

        return new RegisterAttendeeResult(attendee, registrationEvent, cateringEvent, swagEvent);
    }


    public Attendee() {

    }

    Attendee(String email, String firstName, String lastName, boolean student, boolean impactedByLayoffs, TShirtSize tShirtSize, MealPreference mealPreference, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.student = student;
        this.impactedByLayoffs = impactedByLayoffs;
        this.tShirtSize = tShirtSize;
        this.mealPreference = mealPreference;
        this.address = address;
    }
}
