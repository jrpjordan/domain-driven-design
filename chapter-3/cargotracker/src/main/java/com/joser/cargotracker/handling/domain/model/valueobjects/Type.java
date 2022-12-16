package com.joser.cargotracker.handling.domain.model.valueobjects;

/**
 * Handling event type. Either requires or prohibits a carrier movement association,
 * It's never optional
 */
public enum Type {

    // Loaded onto voyage from port location
    LOAD(true),

    // Unloaded from voyage to port location
    UNLOAD(true),

    // Received by carrier
    RECEIVE(false),

    // CargoBookingId claimed by recepient
    CLAIM(false),

    //CargoBookingId went through customs
    CUSTOMS(false);

    private final boolean voyageRequired;

    /**
     * Private enum constructor
     * @param voyageRequired whether or not a voyage is associated with this event type
     */
    private Type(boolean voyageRequired) {
        this.voyageRequired = voyageRequired;
    }

    public boolean requiresVoyage() {
        return voyageRequired;
    }

    public boolean prohibitsVoyage() {
        return !voyageRequired;
    }

    public boolean sameValueAs(Type other) {
        return other != null && this.equals(other);
    }
}
