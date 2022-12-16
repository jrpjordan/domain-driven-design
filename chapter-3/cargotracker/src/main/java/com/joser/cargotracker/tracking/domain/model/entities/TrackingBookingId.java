package com.joser.cargotracker.tracking.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TrackingBookingId implements Serializable {

    @Column(name = "booking_id")
    private String bookingId;

    public TrackingBookingId() {}

    public TrackingBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }
}
