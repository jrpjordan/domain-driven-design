package com.joser.cargotracker.booking.domain.model.aggregates;

import com.joser.cargotracker.booking.domain.model.commands.BookCargoCommand;
import com.joser.cargotracker.booking.domain.model.entities.Location;
import com.joser.cargotracker.booking.domain.model.valueobjects.BookingAmount;
import com.joser.cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import com.joser.cargotracker.booking.domain.model.valueobjects.Delivery;
import com.joser.cargotracker.booking.domain.model.valueobjects.LastCargoHandledEvent;
import com.joser.cargotracker.booking.domain.model.valueobjects.RouteSpecification;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cargo")
@NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
@NamedQuery(name = "Cargo.findByBookingId", query = "SELECT c FROM Cargo c WHERE c.bookingId = :bookingId")
@NamedQuery(name = "Cargo.getAllBookingIds", query = "SELECT c.bookingId FROM Cargo c")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggregate Identifier, object embedded into Cargo
    @Embedded
    private BookingId bookingId;

    @Embedded
    private BookingAmount bookingAmount;

    // Origin of the cargo
    @Embedded
    private Location origin;

    @Embedded
    private RouteSpecification routeSpecification;

    // Itinerary assigned to the cargo
    @Embedded
    private CargoItinerary itinerary;

    // Checks the delivery progress of the cargo against the current Route Specification
    @Embedded
    private Delivery delivery;

    public Cargo() {}

    /**
     *  Constructor Comand Handler for a new Cargo booking. Sets the state of the Aggregate
     *  and registers the Cargo Booked Event
     */
    public Cargo(BookCargoCommand bookCargoCommand) {
        this.bookingId = new BookingId(bookCargoCommand.getBookingId());
        this.routeSpecification = new RouteSpecification(
                new Location(bookCargoCommand.getOriginLocation()),
                new Location(bookCargoCommand.getDestLocation()),
                bookCargoCommand.getDestArrivalDeadline()
        );
        this.origin = routeSpecification.getOrigin();
        this.bookingAmount = new BookingAmount(bookCargoCommand.getBookingAmount());
        this.itinerary = CargoItinerary.EMPTY_ITINERARY; //Empty itinerary since the Cargo has not been routed yet
        this.delivery = Delivery.derivedFrom(
                this.routeSpecification,
                this.itinerary,
                LastCargoHandledEvent.EMPTY
        );
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getOrigin() {
        return origin;
    }

    public RouteSpecification getRouteSpecification() {
        return this.routeSpecification;
    }

    public BookingAmount getBookingAmount() {
        return this.bookingAmount;
    }

    public void setBookingAmount(BookingAmount bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    /**
     * Assigns Route to the Cargo
     * @param cargoItinerary
     */
    public void assignToRoute(CargoItinerary cargoItinerary) {
        this.itinerary = cargoItinerary;
    }

}
