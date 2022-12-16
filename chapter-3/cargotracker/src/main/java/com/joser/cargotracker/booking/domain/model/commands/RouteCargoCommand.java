package com.joser.cargotracker.booking.domain.model.commands;

/**
 * Class to assign a route to a booked cargo
 */
public class RouteCargoCommand {

    private String cargoBookingId;

    public RouteCargoCommand() {}

    public RouteCargoCommand(String cargoBookingId) {
        this.cargoBookingId = cargoBookingId;
    }

    public String getCargoBookingId() {
        return cargoBookingId;
    }

    public void setCargoBookingId(String cargoBookingId) {
        this.cargoBookingId = cargoBookingId;
    }
}
