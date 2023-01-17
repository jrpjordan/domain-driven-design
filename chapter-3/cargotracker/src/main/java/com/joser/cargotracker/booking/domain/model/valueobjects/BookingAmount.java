package com.joser.cargotracker.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BookingAmount {

    @Column(name = "booking_amount")
    private Integer bookingAmount;

    public BookingAmount() {}

    public BookingAmount(Integer bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public void setBookingAmount(Integer bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public Integer getBookingAmount() {
        return this.bookingAmount;
    }
}
