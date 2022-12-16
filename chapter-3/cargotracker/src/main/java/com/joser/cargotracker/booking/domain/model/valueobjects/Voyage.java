package com.joser.cargotracker.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Voyage {

    @Column(name = "voyage_number")
    private String voyageId;

    public Voyage() {}

    public Voyage(String voyageId) {
        this.voyageId = voyageId;
    }

    public String getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(String voyageId) {
        this.voyageId = voyageId;
    }
}
