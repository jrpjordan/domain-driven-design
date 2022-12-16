package com.joser.cargotracker.routing.domain.model.valueobjects;

import com.joser.cargotracker.routing.domain.model.entities.CarrierMovement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Collections;
import java.util.List;

@Embeddable
public class Schedule {

    public static final Schedule EMPTY = new Schedule();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "voyage_id")
    private List<CarrierMovement> carrierMovements = Collections.emptyList();

    public Schedule() {}

    public Schedule(List<CarrierMovement> carrierMovements) {
        this.carrierMovements = carrierMovements;
    }

    public List<CarrierMovement> getCarrierMovements() {
        return Collections.unmodifiableList(carrierMovements);
    }
}
