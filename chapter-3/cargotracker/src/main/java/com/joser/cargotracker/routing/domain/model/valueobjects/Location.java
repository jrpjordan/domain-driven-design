package com.joser.cargotracker.routing.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Location class represented by a unique 5-digit UN Location code.
 */
@Embeddable
public class Location {

    @Column(name = "arrival_location_id")
    private String unLocCode;

    public Location() {}

    public Location(String unLocCode) {
        this.unLocCode = unLocCode;
    }

    public String getUnLocCode() {
        return unLocCode;
    }

    public void setUnLocCode(String unLocCode) {
        this.unLocCode = unLocCode;
    }
}
