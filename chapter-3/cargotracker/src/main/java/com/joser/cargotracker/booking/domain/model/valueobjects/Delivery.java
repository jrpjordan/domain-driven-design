package com.joser.cargotracker.booking.domain.model.valueobjects;

import com.joser.cargotracker.booking.domain.model.entities.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


import java.util.Date;

@Embeddable
public class Delivery {

    public static final Date ETA_UNKNOWN = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "routing_status")
    private RoutingStatus routingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_status")
    private TransportStatus transportStatus;


    @Column(name = "last_known_location_id")
    @AttributeOverride(name = "unLocCode", column = @Column(name = "last_known_location_id"))
    private Location lastKnownLocation;

    @Column(name = "current_voyage_number")
    @AttributeOverride(name = "voyageId", column = @Column(name = "current_voyage_number"))
    private Voyage currentVoyage;

    @Embedded
    private LastCargoHandledEvent lastEvent;

    // Predictions for the Cargo activity. Helps the operator in determining if anything needs to be changed for the future.
    public static final CargoHandlingActivity NO_ACTIVITY = new CargoHandlingActivity();

    @Embedded
    private CargoHandlingActivity nextExpectedActivity;

    public Delivery() { }

    public Delivery(LastCargoHandledEvent lastEvent, CargoItinerary itinerary,
                    RouteSpecification routeSpecification) {
        this.lastEvent = lastEvent;

        // TODO: Implement the method to calculate the status of the route
        //this.routingStatus = calculateRoutingStatus(itinerary, routeSpecification);
        this.transportStatus = calculateTransportStatus();
        this.lastKnownLocation = calculateLastKnownLocation();
        this.currentVoyage = calculateCurrentVoyage();
        // this.nextExpectedActivity = calculateNextExpectedActivity(routeSpecification, itinerary)
    }

    /**
     * Creates a new delivery snapshot to reflect changes in routing, i.e. when
     * the route specification or the itinerary has changed but no additional handling
     * of the cargo has been performed
     */
    public Delivery updateOnRouting(RouteSpecification routeSpecification, CargoItinerary itinerary) {
        return new Delivery(this.lastEvent, itinerary, routeSpecification);
    }

    /**
     * @param routeSpecification
     * @param itinerary
     * @param lastCargoHandledEvent
     * @return
     */
    public static Delivery derivedFrom(RouteSpecification routeSpecification, CargoItinerary itinerary,
                              LastCargoHandledEvent lastCargoHandledEvent) {
        return new Delivery(lastCargoHandledEvent, itinerary, routeSpecification);
    }

    /**
     * Method to calculate the Routing status of a Cargo
     *
     * @param
     * @param
     * @return
     */
    private TransportStatus calculateTransportStatus() {
        if (lastEvent.getHandlingEventType() == null) {
            return TransportStatus.NOT_RECEIVED;
        }

        switch (lastEvent.getHandlingEventType()) {
            case "LOAD":
                return TransportStatus.ONBOARD_CARRIER;
            case "UNLOAD":
            case "RECEIVE":
            case "CUSTOMS":
                return TransportStatus.IN_PORT;
            case "CLAIM":
                return TransportStatus.CLAIMED;
            default:
                return TransportStatus.UNKNOWN;
        }
    }

    /**
     * Calculate Last known location
     * @return
     */
    private Location calculateLastKnownLocation() {
        if (lastEvent != null) {
            return new Location(lastEvent.getHandlingEventLocation());
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    private Voyage calculateCurrentVoyage() {
        if (getTransportStatus().equals(TransportStatus.ONBOARD_CARRIER) && lastEvent != null) {
            return new Voyage(lastEvent.getHandlingEventVoyage());
        } else {
            return null;
        }
    }


    public RoutingStatus getRoutingStatus() { return this.routingStatus;}
    public TransportStatus getTransportStatus() { return this.transportStatus;}
    public Location getLastKnownLocation() {
        return this.lastKnownLocation;
    }
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }
    public void setLastEvent(LastCargoHandledEvent lastEvent) {
        this.lastEvent = lastEvent;
    }
    public Voyage getCurrentVoyage() {
        return this.currentVoyage;
    }
}
