package com.joser.cargotracker.handling.domain.model.commands;


import java.util.Date;

/**
 * Command to Register an Handling Activity
 */
public class HandlingActivityRegistrationCommand {

    private Date completionTime;

    private String bookingId;

    private String voyageNumber;

    private String unLocode;

    private String handlingType;

    public HandlingActivityRegistrationCommand( String bookingId, String voyageNumber, String unLocode,
                                                String handlingType, Date completionTime) {
        this.completionTime = completionTime;
        this.bookingId = bookingId;
        this.voyageNumber = voyageNumber;
        this.unLocode = unLocode;
        this.handlingType = handlingType;
    }

    public HandlingActivityRegistrationCommand() {}

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
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
}
