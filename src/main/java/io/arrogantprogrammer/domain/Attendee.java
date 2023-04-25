package io.arrogantprogrammer.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Attendee extends PanacheEntity {

    String email;

    String firstName;

    String lastName;

    boolean student;

    boolean impactedByLayoffs;

    TShirtSize tShirtSize;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;

    MealPreference mealPreference;

    static RegisterAttendeeResult registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        Attendee attendee = new Attendee();
        attendee.email = registerAttendeeCommand.email();
        attendee.firstName = registerAttendeeCommand.firstName();
        attendee.lastName = registerAttendeeCommand.lastName();
        attendee.student = registerAttendeeCommand.student();
        attendee.impactedByLayoffs = registerAttendeeCommand.impactedByLayoffs();
        attendee.tShirtSize = registerAttendeeCommand.tShirtSize();
        attendee.mealPreference = registerAttendeeCommand.mealPreference();

        attendee.address = new Address();
        attendee.address.street = registerAttendeeCommand.streetAddress();
        attendee.address.street2 = registerAttendeeCommand.streetAddress2();
        attendee.address.city = registerAttendeeCommand.city();
        attendee.address.stateOrProvince = registerAttendeeCommand.stateOrProvince();
        attendee.address.countryCode = registerAttendeeCommand.countryCode();
        attendee.address.postalCode = registerAttendeeCommand.postalCode();

        RegistrationEvent registrationEvent = new RegistrationEvent(attendee.email);
        CateringEvent cateringEvent = new CateringEvent(attendee.mealPreference);
        SwagEvent swagEvent = new SwagEvent(attendee.tShirtSize);

        return new RegisterAttendeeResult(attendee, registrationEvent, cateringEvent, swagEvent);
    }

    Double calculatePrice(final Double basePrice) {
        if (student) {
            return (basePrice - (basePrice * 0.10));
        } else if (impactedByLayoffs) {
            return (basePrice - (basePrice * 0.50));
        }else{
            return basePrice;
        }
    }

    AttendeeInfoValueObject getAttendeeInfo() {
        return new AttendeeInfoValueObject(
                this.email,
                this.firstName,
                this.lastName,
                this.student,
                this.impactedByLayoffs,
                this.tShirtSize,
                this.mealPreference
        );
    }

    public Attendee() {

    }
}
