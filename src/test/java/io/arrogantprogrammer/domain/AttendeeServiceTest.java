package io.arrogantprogrammer.domain;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AttendeeServiceTest {

    @Inject
    AttendeeRepository attendeeRepository;

    @Inject
    AttendeeService attendeeService;

    @Test @TestTransaction
    public void testListAll() {
        attendeeRepository.persist(TestUtility.LemmyKilminster);
        List<AttendeeInfoValueObject> attendeeInfoValueObjectList = attendeeService.listAll();
        assertEquals(1, attendeeInfoValueObjectList.size());
    }


}
