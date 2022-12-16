package com.joser.cargotracker.booking.interfaces.rest;

import com.joser.cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import com.joser.cargotracker.booking.interfaces.rest.dto.RouteCargoResource;
import com.joser.cargotracker.booking.interfaces.rest.transform.RouteCargoCommandDTOAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cargorouting")
@ApplicationScoped
public class CargoRoutingController {

    @Inject
    private CargoBookingCommandService cargoBookingCommandService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response routeCargo(RouteCargoResource routeCargoResource) {
        cargoBookingCommandService.assignRouteToCargo(
                RouteCargoCommandDTOAssembler.toCommandFromDTO(routeCargoResource)
        );
        final Response returnValue = Response.ok()
                .entity("Cargo Routed")
                .build();
        return returnValue;
    }
}
