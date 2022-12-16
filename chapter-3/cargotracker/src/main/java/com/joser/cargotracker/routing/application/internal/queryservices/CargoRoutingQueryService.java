package com.joser.cargotracker.routing.application.internal.queryservices;

import com.joser.cargotracker.routing.domain.model.aggregates.Voyage;
import com.joser.cargotracker.routing.infrastructure.repositories.jpa.VoyageRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Application Service class for the Cargo Routing Query service
 */
@ApplicationScoped
public class CargoRoutingQueryService {

    @Inject
    private VoyageRepository voyageRepository;

    /**
     * Returns all voyages
     * @return
     */
    @Transactional
    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }
}
