package com.joser.cargotracker.booking.domain.model.valueobjects;

import com.joser.cargotracker.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;


import java.util.Date;

@Embeddable
public class RouteSpecification {

    private static final long serialVersionUID = 1L;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "spec_origin_id"))
    private Location origin;

    @Embedded
    @AttributeOverride(name = "unLocCode", column = @Column(name = "spec_destination_id"))
    private Location destination;

    @Temporal(TemporalType.DATE)
    @Column(name = "spec_arrival_deadline")
    @NotNull
    private Date arrivalDeadline;

    public RouteSpecification() {}

    /**
     *
     * @param origin origin location - can't be the same as the destination
     * @param destination destination location - can't be the same as the origin
     * @param arrivalDeadline arrival deadline
     */
    public RouteSpecification(Location origin, Location destination, Date arrivalDeadline) {
        this.origin = origin;
        this.destination = destination;
        this.arrivalDeadline = (Date) arrivalDeadline.clone();
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public Date getArrivalDeadline() {
        return arrivalDeadline;
    }
}
