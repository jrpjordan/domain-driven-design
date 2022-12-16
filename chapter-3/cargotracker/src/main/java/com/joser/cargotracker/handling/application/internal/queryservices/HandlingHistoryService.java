package com.joser.cargotracker.handling.application.internal.queryservices;

import com.joser.cargotracker.handling.domain.model.valueobjects.HandlingActivityHistory;
import com.joser.cargotracker.handling.infrastructure.repositories.jpa.HandlingActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Application Service which caters to all queries related to the Handling Activity Aggregate
 */
@ApplicationScoped
public class HandlingHistoryService {

    @Inject
    private HandlingActivityRepository handlingActivityRepository;

    public HandlingActivityHistory getHandlingActivityHistory(String bookingId) {
        return handlingActivityRepository.lookupHandlingHistoryOfCargo(bookingId);
    }
}
