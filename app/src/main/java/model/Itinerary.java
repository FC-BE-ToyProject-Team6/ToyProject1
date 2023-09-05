package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Itinerary {
    private int itineraryId;
    private String departurePlace;
    private String destination;
    private DateTime departureTime;
    private DateTime arrivalTime;
    private DateTime checkIn;
    private DateTime checkOut;

    public Itinerary(int itineraryId, String departurePlace, String destination, DateTime departureTime, DateTime arrivalTime, DateTime checkIn, DateTime checkOut) {
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




    public int getItineraryId() {
        return itineraryId;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public DateTime getCheckIn() {
        return checkIn;
    }

    public DateTime getCheckOut() {
        return checkOut;
    }
    
    public void setDeparturePlace(String next) {
    }

    public void setDestination(String next) {
    }

    public void setDepartureTime(String next) {
    }

    public void setArrivalTime(String next) {
    }

    public void setCheckIn(String next) {
    }

    public void setCheckOut(String next) {
    }
}