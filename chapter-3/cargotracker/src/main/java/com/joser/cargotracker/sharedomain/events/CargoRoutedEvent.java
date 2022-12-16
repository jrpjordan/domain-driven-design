package com.joser.cargotracker.sharedomain.events;

public class CargoRoutedEvent {

    private CargoRoutedEventData cargoRoutedEventData;

    public CargoRoutedEvent() {}

    public void setContent(CargoRoutedEventData cargoRoutedEventData) {
        this.cargoRoutedEventData = cargoRoutedEventData;
    }

    public CargoRoutedEventData getContent() {
        return this.cargoRoutedEventData;
    }
}
