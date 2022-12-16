package com.joser.cargotracker.tracking.domain.model.commands;

import java.util.Date;

public class AddTrackingEventCommand {

    private String bookingId;

    private Date eventTime;

    private String eventType;

    private String location;

    private String voyageNumber;

    public AddTrackingEventCommand() {
    }

    public AddTrackingEventCommand(String bookingId, Date eventTime,
                                   String eventType, String location, String voyageNumber) {
        this.bookingId = bookingId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.location = location;
        this.voyageNumber = voyageNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }
}
