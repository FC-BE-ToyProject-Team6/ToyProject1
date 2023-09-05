package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Itinerary {
    private final int itineraryId;
    private final String departurePlace;
    private final String destination;
    private final DateTime departureTime;
    private final DateTime arrivalTime;
    private final DateTime checkIn;
    private final DateTime checkOut;
}