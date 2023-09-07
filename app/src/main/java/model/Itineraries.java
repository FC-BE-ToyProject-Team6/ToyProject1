package model;

import java.util.ArrayList;
import java.util.List;

public class Itineraries {

    List<Itinerary> itineraries;

    public Itineraries() {
        this.itineraries = new ArrayList<>();
    }

    public void add(Itinerary itinerary) {
        this.itineraries.add(itinerary);
    }

    public int size() {
        return itineraries.size();
    }

    public Itinerary get(int itineraryId) {
        return itineraries.get(itineraryId);
    }

    public List<Itinerary> getList() {
        return itineraries;

    }
}
