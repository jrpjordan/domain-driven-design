package com.joser.cargotracker.booking.interfaces.rest;


import com.joser.cargotracker.booking.application.internal.commandservices.CargoBookingCommandService;
import com.joser.cargotracker.booking.domain.model.aggregates.BookingId;
import com.joser.cargotracker.booking.interfaces.rest.dto.BookCargoResource;
import com.joser.cargotracker.booking.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cargobooking")
@ApplicationScoped
public class CargoBookingController {

    @Inject
    private CargoBookingCommandService cargoBookingCommandService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response bookCargo(BookCargoResource bookCargoResource) {
        System.out.println("***** Book Cargo " + cargoBookingCommandService);
        BookingId bookingId = cargoBookingCommandService.bookCargo(
                BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoResource));
        final Response returnValue = Response.ok()
                .entity(bookingId)
                .build();
        return returnValue;
    }
}
