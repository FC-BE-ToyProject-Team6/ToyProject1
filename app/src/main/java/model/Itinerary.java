package model;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Itinerary {

    private int itineraryId;
    private String departurePlace;
    private String destination;
    private DateTime departureTime;
    private DateTime arrivalTime;
    private DateTime checkIn;
    private DateTime checkOut;

    public Itinerary(int itineraryId, String departurePlace, String destination,
        DateTime departureTime, DateTime arrivalTime, DateTime checkIn, DateTime checkOut) {
        this.itineraryId = itineraryId;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Itinerary() {
    }

    public Itinerary(String departurePlace, String destination, String departureTime,
        String arrivalTime, String checkIn, String checkOut) {

        this.departurePlace = departurePlace;
        this.destination = destination;
        this.departureTime = DateTime.ofString(departureTime);
        this.arrivalTime = DateTime.ofString(arrivalTime);
        this.checkIn = DateTime.ofString(checkIn);
        this.checkOut = DateTime.ofString(checkOut);
    }


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    public void add(Itinerary itinerary) {
    }
}