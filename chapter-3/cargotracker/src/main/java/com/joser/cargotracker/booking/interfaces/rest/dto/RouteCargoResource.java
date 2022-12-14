package com.joser.cargotracker.booking.interfaces.rest.dto;

/**
 * Resource class for the Book Cargo Command API
 */
public class RouteCargoResource {

    private String bookingId;

    public RouteCargoResource() {}

    public RouteCargoResource(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
