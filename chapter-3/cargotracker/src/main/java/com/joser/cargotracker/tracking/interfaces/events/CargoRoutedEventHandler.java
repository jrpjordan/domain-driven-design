package com.joser.cargotracker.tracking.interfaces.events;

import com.joser.cargotracker.sharedomain.events.CargoRoutedEvent;
import com.joser.cargotracker.tracking.application.internal.commandservices.AssignTrackingIdCommandService;
import com.joser.cargotracker.tracking.interfaces.events.transform.TrackingDetailsCommandEventAssembler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class CargoRoutedEventHandler {

    @Inject
    private AssignTrackingIdCommandService assignTrackingIdCommandService;

    public void observeCargoRoutedEvent(@Observes CargoRoutedEvent event) {
        System.out.println("**** Observing Cargo Routed Event ****");
        assignTrackingIdCommandService.assignTrackingNumberToCargo(
                TrackingDetailsCommandEventAssembler.toCommandFromEvent(event));
    }
}
