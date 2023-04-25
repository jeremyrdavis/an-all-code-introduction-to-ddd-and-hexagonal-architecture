package io.arrogantprogrammer.domain;

import io.smallrye.reactive.messaging.MutinyEmitter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AttendeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeService.class);

    @Inject
    AttendeeRepository attendeeRepository;

    @Inject
    @Channel("registrations")
    MutinyEmitter<RegistrationEvent> registrationEventEmitter;

    @Inject
    @Channel("catering")
    MutinyEmitter<CateringEvent> cateringEventEmitter;

    @Inject
    @Channel("swag")
    MutinyEmitter<SwagEvent> swagEventEmitter;

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
        registrationEventEmitter.sendAndAwait(registerAttendeeResult.registrationEvent());
        cateringEventEmitter.sendAndAwait(registerAttendeeResult.cateringEvent());
        swagEventEmitter.sendAndAwait(registerAttendeeResult.swagEvent());
        LOGGER.debug("persisted: {}", attendee);
    }
}
