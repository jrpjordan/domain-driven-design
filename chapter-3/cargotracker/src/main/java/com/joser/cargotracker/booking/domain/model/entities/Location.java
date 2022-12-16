package com.joser.cargotracker.booking.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    @Column(name = "origin_id")
    private String unLocCode;

    public Location() {}

    public Location(String location) {
        this.unLocCode = location;
    }

    public void setUnLocCode(String unLocCode) {
        this.unLocCode = unLocCode;
    }

    public String getUnLocCode() {
        return this.unLocCode;
    }
}
