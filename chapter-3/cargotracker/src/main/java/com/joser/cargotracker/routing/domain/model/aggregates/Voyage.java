package com.joser.cargotracker.routing.domain.model.aggregates;

import com.joser.cargotracker.routing.domain.model.valueobjects.Schedule;
import com.joser.cargotracker.routing.domain.model.valueobjects.VoyageNumber;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "Voyage.findByVoyageNumber", query = "Select v from Voyage v where v.voyageNumber = :voyageNumber")
@NamedQuery(name = "Voyage.findAll", query = "Select v from Voyage v order by v.voyageNumber")
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voyageId;

    @Embedded
    @NotNull
    private Schedule schedule;

    @Embedded
    private VoyageNumber voyageNumber;

    public Voyage() {}

    public Voyage(VoyageNumber voyageNumber, Schedule schedule){
        this.schedule = schedule;
        this.voyageNumber = voyageNumber;
    }

    public VoyageNumber getVoyageNumber() {
        return voyageNumber;
    }

    public Schedule getSchedule() {
        return schedule;
    }

}
