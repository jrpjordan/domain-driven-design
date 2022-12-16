package com.joser.cargotracker.sharedomain.events;

public class CargoRoutedEventData {

    private String bookingId;

    public CargoRoutedEventData() {}

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
