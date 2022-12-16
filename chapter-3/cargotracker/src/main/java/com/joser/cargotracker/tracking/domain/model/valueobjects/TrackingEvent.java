package com.joser.cargotracker.tracking.domain.model.valueobjects;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tracking_handling_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private TrackingVoyageNumber trackingVoyageNumber;

    @Embedded
    private TrackingLocation trackingLocation;

    @Embedded
    private TrackingEventType trackingEventType;

    public TrackingEvent() {
    }

    public TrackingEvent(TrackingVoyageNumber trackingVoyageNumber,
                         TrackingLocation trackingLocation, TrackingEventType trackingEventType) {
        this.trackingVoyageNumber = trackingVoyageNumber;
        this.trackingLocation = trackingLocation;
        this.trackingEventType = trackingEventType;
    }

    public TrackingVoyageNumber getTrackingVoyageNumber() {
        return trackingVoyageNumber;
    }

    public TrackingLocation getTrackingLocation() {
        return trackingLocation;
    }

    public TrackingEventType getTrackingEventType() {
        return trackingEventType;
    }
}
