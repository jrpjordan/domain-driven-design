package com.joser.cargotracker.tracking.domain.model.aggregates;

import com.joser.cargotracker.tracking.domain.model.commands.AddTrackingEventCommand;
import com.joser.cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;
import com.joser.cargotracker.tracking.domain.model.entities.TrackingActivityEvent;
import com.joser.cargotracker.tracking.domain.model.entities.TrackingBookingId;
import com.joser.cargotracker.tracking.domain.model.valueobjects.TrackingEvent;
import com.joser.cargotracker.tracking.domain.model.valueobjects.TrackingEventType;
import com.joser.cargotracker.tracking.domain.model.valueobjects.TrackingLocation;
import com.joser.cargotracker.tracking.domain.model.valueobjects.TrackingVoyageNumber;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name = "TrackingActivity.findAll",
    query = "Select t from TrackingActivity  t")
@NamedQuery(name = "TrackingActivity.findByTrackingNumber",
    query = "Select t from TrackingActivity t where t.trackingNumber = :trackingNumber")
@NamedQuery(name = "TrackingActivity.getAllTrackingNos",
    query = "Select t.trackingNumber from TrackingActivity t")
@NamedQuery(name = "TrackingActivity.findByBookingNumber",
    query = "Select t from TrackingActivity t where t.bookingId = :bookingId")
@Table(name = "tracking_activity")
public class TrackingActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private TrackingNumber trackingNumber;

    @Embedded
    private TrackingBookingId bookingId;

    @Embedded
    private TrackingActivityEvent trackingActivityEvent;

    public TrackingActivity() {
    }

    /**
     * Creates a new Tracking Number
     * @param assignTrackingNumberCommand
     */
    public TrackingActivity(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        this.trackingNumber = new TrackingNumber(assignTrackingNumberCommand.getTrackingNumber());
        this.bookingId = new TrackingBookingId(assignTrackingNumberCommand.getBookingId());
        this.trackingActivityEvent = TrackingActivityEvent.EMPTY_ACTIVITY;
         /*this.trackingActivityEvent.getTrackingEvents().add(
                new TrackingEvent(
                new TrackingVoyageNumber(""),
                new TrackingLocation(""),
                new TrackingEventType("ROUTED",new Date())));*/
    }

    /**
     * Add a tracking event to the Tracking Details
     * @param addTrackingEventCommand
     */
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand) {
        TrackingEvent trackingEvent = new TrackingEvent(
                new TrackingVoyageNumber(addTrackingEventCommand.getVoyageNumber()),
                new TrackingLocation(addTrackingEventCommand.getLocation()),
                new TrackingEventType(addTrackingEventCommand.getEventType(), addTrackingEventCommand.getEventTime())
        );
        this.trackingActivityEvent.getTrackingEvents().add(trackingEvent);
    }

    public TrackingNumber getTrackingNumber() {
        return trackingNumber;
    }

    public TrackingBookingId getBookingId() {
        return bookingId;
    }

    public TrackingActivityEvent getTrackingActivityEvent() {
        return trackingActivityEvent;
    }
}
