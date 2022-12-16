package com.joser.cargotracker.handling.domain.model.aggregates;

import com.joser.cargotracker.handling.domain.model.valueobjects.CargoBookingId;
import com.joser.cargotracker.handling.domain.model.valueobjects.Location;
import com.joser.cargotracker.handling.domain.model.valueobjects.Type;
import com.joser.cargotracker.handling.domain.model.valueobjects.VoyageNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@NamedQuery(name ="HandlingActivity.findByBookingId"
        , query = "Select e from HandlingActivity e where e.cargoBookingId.bookingId = :bookingId")
@Table(name = "handling_activity")
public class HandlingActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private Type type;

    @Embedded
    private VoyageNumber voyageNumber;

    @Embedded
    private Location location;

    @Temporal(TemporalType.DATE)
    @Column(name = "event_completion_time")
    private Date completionTime;

    @Embedded
    private CargoBookingId cargoBookingId;

    public HandlingActivity() {}

    public HandlingActivity(CargoBookingId cargoBookingId, Date completionTime,
                            Type type,  Location location, VoyageNumber voyageNumber) {
        if (type.prohibitsVoyage()) {
            throw new IllegalArgumentException(
                    "VoyageNumber is not allowed with event type " + type
            );
        }
        this.type = type;
        this.voyageNumber = voyageNumber;
        this.location = location;
        this.completionTime = completionTime;
        this.cargoBookingId = cargoBookingId;
    }

    public HandlingActivity(CargoBookingId cargoBookingId, Date completionTime,
                            Type type, Location location) {
        System.out.println("****Type is **" + type);
        if (type.requiresVoyage()) {
            throw new IllegalArgumentException(
                    "VoyageNumber is required for event type " + type
            );
        }
        this.type = type;
        this.location = location;
        this.completionTime = completionTime;
        this.cargoBookingId = cargoBookingId;
        this.voyageNumber = null;
    }

    public Type getType() {
        return type;
    }

    public VoyageNumber getVoyageNumber() {
        return voyageNumber;
    }

    public Location getLocation() {
        return location;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public CargoBookingId getCargoBookingId() {
        return cargoBookingId;
    }
}
