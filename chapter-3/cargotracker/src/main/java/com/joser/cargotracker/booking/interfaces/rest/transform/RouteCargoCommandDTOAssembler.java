package com.joser.cargotracker.booking.interfaces.rest.transform;

import com.joser.cargotracker.booking.domain.model.commands.RouteCargoCommand;
import com.joser.cargotracker.booking.interfaces.rest.dto.RouteCargoResource;

/**
 * Assembler class to convert the Book Cargo Resource Data to the Book Cargo Model
 */
public class RouteCargoCommandDTOAssembler {

    /**
     * Static method within the Assembler class
     * @param routeCargoResource
     * @return RouteCargoCommand model
     */
    public static RouteCargoCommand toCommandFromDTO(RouteCargoResource routeCargoResource) {
        return new RouteCargoCommand(routeCargoResource.getBookingId());
    }
}
