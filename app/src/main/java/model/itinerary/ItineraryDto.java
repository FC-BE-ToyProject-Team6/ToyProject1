package model.itinerary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.DateTime;

@Getter
@AllArgsConstructor
public class ItineraryDto {

    private String departurePlace;
    private String destination;
    private DateTime departureTime;
    private DateTime arrivalTime;
    private DateTime checkIn;
    private DateTime checkOut;

}
