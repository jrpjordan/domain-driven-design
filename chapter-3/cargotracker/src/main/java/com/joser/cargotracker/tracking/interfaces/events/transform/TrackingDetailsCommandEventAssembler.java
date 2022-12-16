package com.joser.cargotracker.tracking.interfaces.events.transform;

import com.joser.cargotracker.sharedomain.events.CargoRoutedEvent;
import com.joser.cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;

public class TrackingDetailsCommandEventAssembler {

    /**
     * Static method within the Assembler class
     * @param cargoRoutedEvent
     * @return AssignTrackingNumberCommand Model
     */
    public static AssignTrackingNumberCommand toCommandFromEvent(CargoRoutedEvent cargoRoutedEvent){
        System.out.println("****cargoRoutedEvent****"+cargoRoutedEvent.getContent().getBookingId());
        return new AssignTrackingNumberCommand(
                cargoRoutedEvent.getContent().getBookingId(),"");
    }
}
