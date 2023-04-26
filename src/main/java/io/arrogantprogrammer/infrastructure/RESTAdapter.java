package io.arrogantprogrammer.infrastructure;

import io.arrogantprogrammer.domain.*;
import io.smallrye.common.annotation.Blocking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
