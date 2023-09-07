package model;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    public Trip(int tripId, String tripName, String startDate, String endDate, Itineraries itineraries) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = Date.ofString(startDate);
        this.endDate = Date.ofString(endDate);
        this.itineraries = itineraries;
    }
  
    public Trip(String tripName, String startDate, String endDate) {
        this.tripName = tripName;
        this.startDate = Date.ofString(startDate);
        this.endDate = Date.ofString(endDate);

    }

    public Trip() {

    }


    public Itineraries getItineraries() {
        return itineraries;
    }



    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public boolean isIdEquals(int tripId) {
        return this.tripId == tripId;
    }
}