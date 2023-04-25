package io.arrogantprogrammer.domain;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class AttendeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeeService.class);

    @Inject
    AttendeeRepository attendeeRepository;

    @Channel("registrations")
    Emitter<RegistrationEvent> registrationEventEmitter;

//    @Channel("catering")
//    Emitter<CateringEvent> cateringEventEmitter;
//
//    @Channel("swag")
//    Emitter<SwagEvent> swagEventEmitter;

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
        registrationEventEmitter.send(registerAttendeeResult.registrationEvent());
        LOGGER.debug("persisted: {}", attendee);
    }
}
