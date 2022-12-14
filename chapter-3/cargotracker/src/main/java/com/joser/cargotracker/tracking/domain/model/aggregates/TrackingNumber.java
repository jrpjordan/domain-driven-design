package com.joser.cargotracker.tracking.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TrackingNumber {

    @Column(name = "tracking_number")
    private String trackingNumber;

    public TrackingNumber() {}

    public TrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
}
