package model;

import lombok.Getter;

import java.util.List;


public class Itineraries {

    List<Itinerary> itineraries;

    public Itineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public void add(Itinerary itinerary) {
    }


    public Itinerary get(int itineraryId) {
        return itineraries.get(itineraryId);
    }
}
