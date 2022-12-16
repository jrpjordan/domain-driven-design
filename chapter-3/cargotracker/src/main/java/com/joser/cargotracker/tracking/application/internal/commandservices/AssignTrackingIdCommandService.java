package com.joser.cargotracker.tracking.application.internal.commandservices;

import com.joser.cargotracker.tracking.domain.model.aggregates.TrackingActivity;
import com.joser.cargotracker.tracking.domain.model.aggregates.TrackingNumber;
import com.joser.cargotracker.tracking.domain.model.commands.AddTrackingEventCommand;
import com.joser.cargotracker.tracking.domain.model.commands.AssignTrackingNumberCommand;
import com.joser.cargotracker.tracking.domain.model.entities.TrackingBookingId;
import com.joser.cargotracker.tracking.infrastructure.repositories.jpa.TrackingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AssignTrackingIdCommandService {

    @Inject
    private TrackingRepository trackingRepository;

    /**
     * Service Command method to assign a tracking id to the booked cargo
     * @param assignTrackingNumberCommand
     * @return Tracking Number of the Cargo
     */
    @Transactional
    public TrackingNumber assignTrackingNumberToCargo(AssignTrackingNumberCommand assignTrackingNumberCommand) {
        String trackingNumber = trackingRepository.nextTrackingNumber();
        assignTrackingNumberCommand.setTrackingNumber(trackingNumber);
        TrackingActivity activity = new TrackingActivity(assignTrackingNumberCommand);

        System.out.println("**** Going to store in repository ******");
        trackingRepository.store(activity);

        return new TrackingNumber(trackingNumber);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param addTrackingEventCommand
     */
    @Transactional
    public void addTrackingEvent(AddTrackingEventCommand addTrackingEventCommand) {
        TrackingActivity trackingActivity = trackingRepository.findByBookingId(
                new TrackingBookingId(addTrackingEventCommand.getBookingId())
        );
        trackingActivity.addTrackingEvent(addTrackingEventCommand);
        trackingRepository.store(trackingActivity);
    }
}
