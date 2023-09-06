package io.arrogantprogrammer.attendees.infrastructure;

import io.arrogantprogrammer.attendees.domain.AttendeeService;
import io.arrogantprogrammer.attendees.domain.RegisterAttendeeCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/attendees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RESTAdapter.class);

    @Inject
    AttendeeService attendeeService;

    @POST
    @Transactional
    public Response registerAttendee(RegisterAttendeeCommand registerAttendeeCommand) {

        LOGGER.debug("RegisterAttendeeCommand received: {}", registerAttendeeCommand);

        attendeeService.registerAttendee(registerAttendeeCommand);

        return Response.accepted().build();
    }

    @GET
    public Response allAttendees() {
        return Response.ok().entity(attendeeService.listAll()).build();
    }

}
