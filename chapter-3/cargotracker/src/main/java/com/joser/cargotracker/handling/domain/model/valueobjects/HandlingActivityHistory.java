package com.joser.cargotracker.handling.domain.model.valueobjects;

import com.joser.cargotracker.handling.domain.model.aggregates.HandlingActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class HandlingActivityHistory {

    private final List<HandlingActivity> handlingEvents;

    public static final HandlingActivityHistory EMPTY = new HandlingActivityHistory(
            Collections.<HandlingActivity>emptyList());

    public HandlingActivityHistory(List<HandlingActivity> handlingEvents) {
        this.handlingEvents = handlingEvents;
    }

    public List<HandlingActivity> getAllHandlingEvents() {
        return handlingEvents;
    }

    /**
     *
     * @return A distinct list (no duplicate registrations) of handling events,
     * ordered by completion time
     */
    public List<HandlingActivity> getDistinctEventsByCompletionTime() {
        List<HandlingActivity> ordered = new ArrayList<>(new HashSet<>(
                handlingEvents));
        Collections.sort(ordered, BY_COMPLETION_TIME_COMPARATOR);
        return Collections.unmodifiableList(ordered);
    }

    /**
     *
     * @return Most recently completed event, or null if the delivery history is empty.
     */
    public HandlingActivity getMostRecentlyCompletedEvent() {
        List<HandlingActivity> distinctEvents = getDistinctEventsByCompletionTime();

        if (distinctEvents.isEmpty()) {
            return null;
        } else {
            return distinctEvents.get(distinctEvents.size() - 1);
        }
    }

    private boolean sameValueAs(HandlingActivityHistory other) {
        return other != null && this.handlingEvents.equals(other.getAllHandlingEvents());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlingActivityHistory that = (HandlingActivityHistory) o;
        return Objects.equals(handlingEvents, that.handlingEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handlingEvents);
    }

    private static final Comparator<HandlingActivity> BY_COMPLETION_TIME_COMPARATOR =
            Comparator.comparing(HandlingActivity::getCompletionTime);

}
