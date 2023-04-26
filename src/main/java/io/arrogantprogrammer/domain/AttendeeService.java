package io.arrogantprogrammer.domain;

import io.smallrye.reactive.messaging.MutinyEmitter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AttendeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeService.class);

    @Inject
    AttendeeRepository attendeeRepository;

    @Inject
    @Channel("registrations")
    MutinyEmitter<RegistrationEvent> registrationEventAdapter;

    @Inject
    @Channel("catering")
    MutinyEmitter<CateringEvent> cateringEventAdapter;

    @Inject
    @Channel("swag")
    MutinyEmitter<SwagEvent> swagEventAdapter;

    /**
     * 1. Create an Attendee object
     * 2. Persist the Attendee object
     * 3. Notify external systems that an Attendee has registered
     * 4. Notify the catering domain of meal requirements
     * 5. Notify the swag domain of tshirt size
     *
     * @param registerAttendeeCommand
     */
    public void registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        LOGGER.debug("RegisterAttendeeCommand received: {}", registerAttendeeCommand);

        RegisterAttendeeResult registerAttendeeResult = Attendee.registerAttendee(registerAttendeeCommand);
        Attendee attendee = registerAttendeeResult.attendee();
        attendeeRepository.persist(attendee);
        registrationEventAdapter.sendAndAwait(registerAttendeeResult.registrationEvent());
        cateringEventAdapter.sendAndAwait(registerAttendeeResult.cateringEvent());
        swagEventAdapter.sendAndAwait(registerAttendeeResult.swagEvent());

        LOGGER.debug("persisted: {}", attendee);
    }

    public List<AttendeeInfoValueObject> listAll() {
        return attendeeRepository.listAll().stream().map(attendee -> {
            return new AttendeeInfoValueObject(attendee.email,
                    attendee.firstName,
                    attendee.lastName,
                    attendee.address.city,
                    attendee.address.stateOrProvince,
                    attendee.address.countryCode);
        }).collect(Collectors.toList());
    }
}
