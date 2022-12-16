package com.joser.cargotracker.booking.infrastructure.repositories.jpa;

import com.joser.cargotracker.booking.domain.model.aggregates.BookingId;
import com.joser.cargotracker.booking.domain.model.aggregates.Cargo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Repository class for the Cargo Aggregate. Deals with all repository operations
 * related to the state of the Cargo
 */
@ApplicationScoped
public class CargoRepository {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(CargoRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Returns the Cargo Aggregate based on the Booking Identifier of a Cargo
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(BookingId bookingId) {
        Cargo cargo = null;
        try {
            cargo = entityManager.createNamedQuery("Cargo.findByBookingId", Cargo.class)
                    .setParameter("bookingId", bookingId)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.log(Level.FINE, "Find called on non-existing BookingID", e);
        }
        return cargo;
    }

    /**
     * Stores the Cargo Aggregate
     * @param cargo
     */
    public void store(Cargo cargo) {
        System.out.println("***** Entity Manager is ****" + entityManager);
        entityManager.persist(cargo);
        entityManager.flush();
    }

    /**
     * Gets next Booking Identifier
     * @return
     */
    public String nextBookingId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return random.substring(0, random.indexOf("-"));
    }

    public List<Cargo> findAll() {
        return entityManager.createNamedQuery("Cargo.findAll", Cargo.class)
                .getResultList();
    }

    public List<BookingId> findAllBookingIds() {
        List<BookingId> bookingIds = new ArrayList<>();

        try {
            bookingIds = entityManager.createNamedQuery(
                    "Cargo.getAllBookingIds", BookingId.class).getResultList();
        } catch (NoResultException e) {
                logger.log(Level.FINE, "Unable to get all tracking IDs", e);
        }
        return bookingIds;

    }
}
