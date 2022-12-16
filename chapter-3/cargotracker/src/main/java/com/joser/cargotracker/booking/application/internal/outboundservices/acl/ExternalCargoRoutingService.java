package com.joser.cargotracker.booking.application.internal.outboundservices.acl;

import com.joser.cargotracker.booking.domain.model.entities.Location;
import com.joser.cargotracker.booking.domain.model.valueobjects.CargoItinerary;
import com.joser.cargotracker.booking.domain.model.valueobjects.Leg;
import com.joser.cargotracker.booking.domain.model.valueobjects.RouteSpecification;
import com.joser.cargotracker.booking.domain.model.valueobjects.Voyage;
import com.joser.cargotracker.booking.infrastructure.services.http.ExternalCargoRoutingClient;
import com.joser.cargotracker.sharedomain.model.TransitEdge;
import com.joser.cargotracker.sharedomain.model.TransitPath;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Anti Corruption Service Class
 */
@ApplicationScoped
public class ExternalCargoRoutingService {

    @Inject
    private ExternalCargoRoutingClient externalCargoRoutingClient;

    /**
     * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
     * fetch the Optimal Itinerary for a Cargo based on the Route Specification
     * @param routeSpecification
     * @return
     */
    public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification) {
        TransitPath transitPath = externalCargoRoutingClient.findOptimalRoute(
                routeSpecification.getOrigin().getUnLocCode(),
                routeSpecification.getDestination().getUnLocCode(),
                routeSpecification.getArrivalDeadline().toString()
        );

        List<Leg> legs = new ArrayList<Leg>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()) {
            legs.add(toLeg(edge));
        }

        return new CargoItinerary(legs);
    }

    /**
     * Anti-corruption layer conversion method from the routing service's domain model (TransitEdges)
     * to the domain model recognized by the Booking Bounded Context (Legs)
     * @param edge
     * @return leg
     */
    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLocode()),
                new Location(edge.getToUnLocode()),
                edge.getFromDate(),
                edge.getToDate()
        );
    }
}
