package com.joser.cargotracker.booking.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LastCargoHandledEvent {

    @Column(name = "last_handling_event_id")
    private Integer handlingEventId;

    @Column(name = "last_handling_event_type")
    private String handlingEventType;

    @Column(name = "last_handling_event_voyage")
    private String handlingEventVoyage;

    @Column(name = "last_handling_event_location")
    private String handlingEventLocation;

    //Null object pattern
    public static final LastCargoHandledEvent EMPTY = new LastCargoHandledEvent();

    public LastCargoHandledEvent() {}

    public LastCargoHandledEvent(Integer handlingEventId, String handlingEventType,
                                 String handlingEventVoyage, String handlingEventLocation) {
        this.handlingEventId = handlingEventId;
        this.handlingEventType = handlingEventType;
        this.handlingEventVoyage = handlingEventVoyage;
        this.handlingEventLocation = handlingEventLocation;
    }

    public Integer getHandlingEventId() {
        return handlingEventId;
    }

    public void setHandlingEventId(Integer handlingEventId) {
        this.handlingEventId = handlingEventId;
    }

    public String getHandlingEventType() {
        return handlingEventType;
    }

    public void setHandlingEventType(String handlingEventType) {
        this.handlingEventType = handlingEventType;
    }

    public String getHandlingEventVoyage() {
        return handlingEventVoyage;
    }

    public void setHandlingEventVoyage(String handlingEventVoyage) {
        this.handlingEventVoyage = handlingEventVoyage;
    }

    public String getHandlingEventLocation() {
        return handlingEventLocation;
    }

    public void setHandlingEventLocation(String handlingEventLocation) {
        this.handlingEventLocation = handlingEventLocation;
    }
}
