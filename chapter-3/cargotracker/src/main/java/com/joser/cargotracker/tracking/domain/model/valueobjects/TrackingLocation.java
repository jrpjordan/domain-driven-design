package com.joser.cargotracker.tracking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TrackingLocation {

    @Column(name = "location_id")
    private String unLocCode;

    public TrackingLocation() {}

    public TrackingLocation(String unLocCode) {
        this.unLocCode = unLocCode;
    }

    public String getUnLocCode() {
        return unLocCode;
    }

    public void setUnLocCode(String unLocCode) {
        this.unLocCode = unLocCode;
    }
}
