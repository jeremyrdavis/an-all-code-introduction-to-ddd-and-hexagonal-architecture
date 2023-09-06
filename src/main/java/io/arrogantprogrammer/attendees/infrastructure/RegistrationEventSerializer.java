package io.arrogantprogrammer.attendees.infrastructure;

import io.arrogantprogrammer.attendees.domain.RegistrationEvent;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class RegistrationEventSerializer implements Serializer<RegistrationEvent> {

    @Override
    public byte[] serialize(String s, RegistrationEvent registrationEvent) {
        return registrationEvent.toString().getBytes(StandardCharsets.UTF_8);
    }
}
