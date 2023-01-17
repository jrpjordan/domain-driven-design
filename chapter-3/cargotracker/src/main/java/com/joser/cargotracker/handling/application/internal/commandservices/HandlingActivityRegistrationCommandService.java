package com.joser.cargotracker.handling.application.internal.commandservices;

import com.joser.cargotracker.handling.domain.model.aggregates.HandlingActivity;
import com.joser.cargotracker.handling.domain.model.commands.HandlingActivityRegistrationCommand;
import com.joser.cargotracker.handling.domain.model.valueobjects.CargoBookingId;
import com.joser.cargotracker.handling.domain.model.valueobjects.Location;
import com.joser.cargotracker.handling.domain.model.valueobjects.Type;
import com.joser.cargotracker.handling.domain.model.valueobjects.VoyageNumber;
import com.joser.cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import com.joser.cargotracker.sharedomain.events.CargoHandledEvent;
import com.joser.cargotracker.sharedomain.events.CargoHandledEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class HandlingActivityRegistrationCommandService {

    @Inject
    private HandlingActivityRepository handlingActivityRepository;

    // Event that needs to be raised when the Cargo is handled
    @Inject
    private Event<CargoHandledEvent> cargoHandledEventEventControl;

    @Transactional
    public void registerHandlingActivityService(HandlingActivityRegistrationCommand handlingActivityRegistrationCommand) {
        System.out.println("Handling Voyage Number is " + handlingActivityRegistrationCommand.getVoyageNumber());

        if(!handlingActivityRegistrationCommand.getVoyageNumber().equals("")) {
            HandlingActivity handlingActivity = new HandlingActivity(
                    new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()),
                    new VoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber()));
            handlingActivityRepository.store(handlingActivity);
        } else {
            HandlingActivity handlingActivity = new HandlingActivity(
                    new CargoBookingId(handlingActivityRegistrationCommand.getBookingId()),
                    handlingActivityRegistrationCommand.getCompletionTime(),
                    Type.valueOf(handlingActivityRegistrationCommand.getHandlingType()),
                    new Location(handlingActivityRegistrationCommand.getUnLocode()));
            handlingActivityRepository.store(handlingActivity);
        }

        CargoHandledEvent cargoHandledEvent = new CargoHandledEvent();
        CargoHandledEventData eventData = new CargoHandledEventData();
        eventData.setBookingId(handlingActivityRegistrationCommand.getBookingId());
        eventData.setHandlingCompletionTime(handlingActivityRegistrationCommand.getCompletionTime());
        eventData.setHandlingLocation(handlingActivityRegistrationCommand.getUnLocode());
        eventData.setHandlingType(handlingActivityRegistrationCommand.getHandlingType());
        eventData.setVoyageNumber(handlingActivityRegistrationCommand.getVoyageNumber());

        System.out.println("***** Event Data ****" + eventData);
        cargoHandledEvent.setContent(eventData);

        System.out.println("***** cargohandled " + handlingActivityRegistrationCommand.getBookingId() + " " +
                handlingActivityRegistrationCommand.getHandlingType() + " " +
                handlingActivityRegistrationCommand.getCompletionTime() + " " +
                handlingActivityRegistrationCommand.getUnLocode());

        cargoHandledEventEventControl.fire(cargoHandledEvent);
    }
}
