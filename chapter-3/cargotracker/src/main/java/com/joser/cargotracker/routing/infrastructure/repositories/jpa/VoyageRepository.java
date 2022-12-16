package com.joser.cargotracker.routing.infrastructure.repositories.jpa;

import com.joser.cargotracker.routing.domain.model.aggregates.Voyage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.logging.Logger;

/**
 * Repository class for the Voyage Aggregate
 */
@ApplicationScoped
public class VoyageRepository {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(VoyageRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Stores the Voyage aggregate
     * @param voyage
     */
    public void store(Voyage voyage) {
        entityManager.persist(voyage);
        entityManager.flush();
    }

    public List<Voyage> findAll() {
        return entityManager.createNamedQuery("Voyage.findAll", Voyage.class).getResultList();
    }

}
