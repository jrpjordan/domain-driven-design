package com.joser.cargotracker.booking.application.internal.queryservices;

import com.joser.cargotracker.booking.domain.model.aggregates.BookingId;
import com.joser.cargotracker.booking.domain.model.aggregates.Cargo;
import com.joser.cargotracker.booking.infrastructure.repositories.jpa.CargoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Application Service which caters to all queries related to the Booking Bounded Context
 */
@ApplicationScoped
public class CargoBookingQueryService {

    @Inject
    private CargoRepository cargoRepository;

    /**
     * List All Booking Identifiers
     * @return List<Cargo>
     */
    @Transactional
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
    public List<BookingId> getAllBookingIds() {
        return cargoRepository.findAllBookingIds();
    }

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(String bookingId) {
        return cargoRepository.find(new BookingId(bookingId));
    }
}
