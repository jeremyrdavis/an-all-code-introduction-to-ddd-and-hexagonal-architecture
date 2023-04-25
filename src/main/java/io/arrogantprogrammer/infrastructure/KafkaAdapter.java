package io.arrogantprogrammer.infrastructure;

import io.arrogantprogrammer.domain.AttendeeService;
import io.arrogantprogrammer.domain.RegisterAttendeeCommand;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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
