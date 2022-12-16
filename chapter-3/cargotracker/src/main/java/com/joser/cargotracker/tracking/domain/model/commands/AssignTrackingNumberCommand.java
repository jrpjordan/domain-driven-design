package com.joser.cargotracker.tracking.domain.model.commands;

public class AssignTrackingNumberCommand {

    private String bookingId;

    private String trackingNumber;

    public AssignTrackingNumberCommand() {}

    public AssignTrackingNumberCommand(String bookingId, String trackingNumber) {
        this.bookingId = bookingId;
        this.trackingNumber = trackingNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
