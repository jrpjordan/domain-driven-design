package com.joser.cargotracker.tracking.interfaces.events;

import com.joser.cargotracker.sharedomain.events.CargoHandledEvent;
import com.joser.cargotracker.sharedomain.events.CargoHandledEventData;
import com.joser.cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import com.joser.cargotracker.tracking.interfaces.events.transform.TrackingActivityCommandEventAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class CargoHandledEventHandler {

    @Inject
    private AssignTrackingIdCommandService assignTrackingIdCommandService;

    public void observeCargoHandledEvent(@Observes CargoHandledEvent event) {
        System.out.println("***** Cargo Handled Event **** " + event.getContent());
        CargoHandledEventData eventData = event.getContent();
        System.out.println(eventData.getBookingId());
        System.out.println(eventData.getHandlingLocation());
        System.out.println(eventData.getHandlingCompletionTime());
        System.out.println(eventData.getHandlingType());
        System.out.println(eventData.getVoyageNumber());
        assignTrackingIdCommandService.addTrackingEvent(
                TrackingActivityCommandEventAssembler.toCommandFromEvent(event));
    }
}
