package com.joser.cargotracker.routing.domain.model.entities;

import com.joser.cargotracker.routing.domain.model.valueobjects.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "carrier_movement")
public class CarrierMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;

    @Embedded
    private Location arrivalLocation;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "departure_location_id"))
    private Location departureLocation;

    public CarrierMovement() {}

    public CarrierMovement(Location departureLocation,
                           Location arrivalLocation, Date departureDate, Date arrivalDate) {

        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

    public Date getArrivalDate() {
        return this.arrivalDate;
    }
}
