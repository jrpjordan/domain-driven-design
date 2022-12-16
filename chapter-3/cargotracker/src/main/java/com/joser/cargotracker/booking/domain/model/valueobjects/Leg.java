package com.joser.cargotracker.booking.domain.model.valueobjects;

import com.joser.cargotracker.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Leg {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Voyage voyage;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "load_location_id"))
    private Location loadLocation;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "unload_location_id"))
    private Location unloadLocation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "load_time")
    @NotNull
    private Date loadTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "unload_time")
    @NotNull
    private Date unLoadTime;

    public Leg() {}

    public Leg(Voyage voyage, Location loadLocation, Location unloadLocation, Date loadTime, Date unLoadTime) {
        this.voyage = voyage;
        this.loadLocation = loadLocation;
        this.unloadLocation = unloadLocation;
        this.loadTime = loadTime;
        this.unLoadTime = unLoadTime;
    }
}
