package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Trip {
    private int tripId;
    private String tripName;
    private Date startDate;
    private Date endDate;
    private Itineraries itineraries;

    public Trip(int tripId, String tripName, Date startDate, Date endDate, Itineraries itineraries) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itineraries = itineraries;
    }

    public int getTripId() {
        return tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Itineraries getItineraries() {
        return itineraries;
    }


}