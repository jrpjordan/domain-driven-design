package com.joser.cargotracker.sharedomain.events;

import java.util.Date;

public class CargoHandledEventData {

    private String bookingId;

    private String handlingType;

    private Date handlingCompletionTime;

    private String handlingLocation;

    private String voyageNumber;

    public CargoHandledEventData(
            String bookingId,
            String handlingType,
            Date handlingCompletionTime,
            String handlingLocation,
            String voyageNumber
    ) {
        this.bookingId = bookingId;
        this.handlingType = handlingType;
        this.handlingCompletionTime = handlingCompletionTime;
        this.handlingLocation = handlingLocation;
        this.voyageNumber = voyageNumber;
    }

    public CargoHandledEventData() {}

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getHandlingType() {
        return handlingType;
    }

    public void setHandlingType(String handlingType) {
        this.handlingType = handlingType;
    }

    public Date getHandlingCompletionTime() {
        return handlingCompletionTime;
    }

    public void setHandlingCompletionTime(Date handlingCompletionTime) {
        this.handlingCompletionTime = handlingCompletionTime;
    }

    public String getHandlingLocation() {
        return handlingLocation;
    }

    public void setHandlingLocation(String handlingLocation) {
        this.handlingLocation = handlingLocation;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }
}
