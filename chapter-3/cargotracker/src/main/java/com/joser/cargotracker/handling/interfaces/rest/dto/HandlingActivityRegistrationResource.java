package com.joser.cargotracker.handling.interfaces.rest.dto;

import java.time.LocalDate;

public class HandlingActivityRegistrationResource {

    private String bookingId;

    private String voyageNumber;

    private String unLocode;

    private String handlingType;

    private LocalDate completionTime;

    public HandlingActivityRegistrationResource(String bookingId, String voyageNumber,
                                                String unLocode, String handlingType, LocalDate completionTime) {
        this.bookingId = bookingId;
        this.voyageNumber = voyageNumber;
        this.unLocode = unLocode;
        this.handlingType = handlingType;
        this.completionTime = completionTime;
    }

    public HandlingActivityRegistrationResource() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public String getUnLocode() {
        return unLocode;
    }

    public void setUnLocode(String unLocode) {
        this.unLocode = unLocode;
    }

    public String getHandlingType() {
        return handlingType;
    }

    public void setHandlingType(String handlingType) {
        this.handlingType = handlingType;
    }

    public LocalDate getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDate completionTime) {
        this.completionTime = completionTime;
    }
}
