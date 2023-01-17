package com.joser.cargotracker.booking.infrastructure.services.http;

import com.joser.cargotracker.sharedomain.model.TransitPath;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

/**
 * Type safe Rest client for the Routing Service API
 */
@ApplicationScoped
public class ExternalCargoRoutingClient {

    public TransitPath findOptimalRoute(String origin, String destination, String arrivalDeadline) {
        final String REST_URI =
                "http://localhost:5050/cargotracker/serviceapi/voyageRouting/optimalRoute";

        Client client = ClientBuilder.newClient();

        return client
                .target(REST_URI)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("deadline", arrivalDeadline)
                .request(MediaType.APPLICATION_JSON)
                .get(TransitPath.class);
    }
}
