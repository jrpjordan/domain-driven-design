package com.joser.cargotracker.tracking.interfaces.events.transform;


import com.joser.cargotracker.sharedomain.events.CargoHandledEvent;
import com.joser.cargotracker.sharedomain.events.CargoHandledEventData;
import com.joser.cargotracker.tracking.domain.model.commands.AddTrackingEventCommand;

public class TrackingActivityCommandEventAssembler {

    /**
     * Static method within the Assembler class
     * @param cargoHandledEvent
     * @return AssignTrackingNumberCommand Model
     */
    public static AddTrackingEventCommand toCommandFromEvent(CargoHandledEvent cargoHandledEvent){
        CargoHandledEventData eventData = cargoHandledEvent.getContent();
        return new AddTrackingEventCommand(
                eventData.getBookingId(),
                eventData.getHandlingCompletionTime(),
                eventData.getHandlingType(),
                eventData.getHandlingLocation(),
                eventData.getVoyageNumber());
    }
}
