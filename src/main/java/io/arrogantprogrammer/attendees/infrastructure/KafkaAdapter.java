package io.arrogantprogrammer.attendees.infrastructure;

import io.arrogantprogrammer.attendees.domain.AttendeeService;
import io.arrogantprogrammer.attendees.domain.RegisterAttendeeCommand;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class KafkaAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaAdapter.class);

    @Inject
    AttendeeService attendeeService;

    @Incoming("reg")
    @Transactional
    public void onRegisterAttendeeCommand(RegisterAttendeeCommand registerAttendeeCommand) {
        LOGGER.debug("RegisterAttendeeCommand received on topic 'reg': {}", registerAttendeeCommand);
        attendeeService.registerAttendee(registerAttendeeCommand);
    }
}
