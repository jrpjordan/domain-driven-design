package com.joser.cargotracker.booking.domain.model.valueobjects;

import com.joser.cargotracker.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

/**
 * A handling activity represents how and where a cargo can be handled, and can be used to
 * express predictions about what is expected to happen to a cargo in the future.
 */
@Embeddable
public class CargoHandlingActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "next_expected_handling_event_type")
    private String type;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "next_expected_location_id"))
    private Location location;

    @Column(name = "next_expected_voyage_id")
    @AttributeOverride(name = "voyageId", column = @Column(name = "next_expected_voyage_id"))
    private Voyage voyage;

    public CargoHandlingActivity() {}

    public CargoHandlingActivity(String type, Location location, Voyage voyage) {
        this.type = type;
        this.location = location;
        this.voyage = voyage;
    }

    public String getType() {
        return this.type;
    }

    public Location getLocation() {
        return this.location;
    }

    public Voyage getVoyage() {
        return this.voyage;
    }
}
