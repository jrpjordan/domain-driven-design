package com.joser.cargotracker.tracking.interfaces.events;

import com.joser.cargotracker.sharedomain.events.CargoBookedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class CargoBookedEventHandler {

    public void testEventObserving(@Observes CargoBookedEvent event) {
        System.out.println("**** Just a Test***** " + event.getId());
    }
}
