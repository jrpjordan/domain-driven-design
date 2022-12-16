package com.joser.cargotracker.booking.application.internal.commandservices;

import com.joser.cargotracker.booking.application.internal.outboundservices.acl.ExternalCargoRoutingService;
import com.joser.cargotracker.booking.domain.model.aggregates.BookingId;
import com.joser.cargotracker.booking.domain.model.aggregates.Cargo;
import com.joser.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.joser.cargotracker.booking.domain.model.commands.RouteCargoCommand;
import com.joser.cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import com.joser.cargotracker.booking.infrastructure.repositories.jpa.CargoRepository;
import com.joser.cargotracker.sharedomain.events.CargoBookedEvent;
import com.joser.cargotracker.sharedomain.events.CargoRoutedEvent;
import com.joser.cargotracker.sharedomain.events.CargoRoutedEventData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Application Service class for the Cargo Booking Command
 */
@ApplicationScoped
public class CargoBookingCommandService {

    // Outbound Service to connect to the Booking Bounded Context MySQL Database Instance
    @Inject
    private CargoRepository cargoRepository;

    @Inject
    private Event<CargoBookedEvent> cargoBookedEventControl;

    // Event that needs to be raised when the Cargo is Booked
    @Inject
    private Event<CargoRoutedEvent> cargoRoutedEventControl;

    @Inject
    private ExternalCargoRoutingService externalCargoRoutingService;

    /**
     * Service Command method to book a new Cargo
     * @return BookingId of the Cargo
     */
    @Transactional
    public BookingId bookCargo(BookCargoCommand bookCargoCommand) {
        String bookingId = cargoRepository.nextBookingId();
        bookCargoCommand.setBookingId(bookingId);
        Cargo cargo = new Cargo(bookCargoCommand);
        cargoRepository.store(cargo);

        CargoBookedEvent cargoBookedEvent = new CargoBookedEvent();
        cargoBookedEvent.setId(bookingId);
        cargoBookedEventControl.fire(cargoBookedEvent);

        return new BookingId(bookingId);
    }

    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */
    @Transactional
    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand) {
        Cargo cargo = cargoRepository.find(new BookingId(routeCargoCommand.getCargoBookingId()));

        CargoItinerary cargoItinerary = externalCargoRoutingService.
                fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoItinerary);
        cargoRepository.store(cargo);

        CargoRoutedEvent cargoRoutedEvent = new CargoRoutedEvent();
        CargoRoutedEventData eventData = new CargoRoutedEventData();
        eventData.setBookingId(routeCargoCommand.getCargoBookingId());
        cargoRoutedEvent.setContent(eventData);
        cargoRoutedEventControl.fire(cargoRoutedEvent);
    }
}
