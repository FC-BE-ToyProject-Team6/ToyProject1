package controller;

import model.*;
import model.dao.*;

import java.util.List;
import java.util.Optional;

public class ItinerarySelectController {

    private static TripDAO tripDAO;
    private static final int CSV_NUMBER = 1, JSON_NUMBER = 2;

    public ItinerarySelectController(int searchMethod) {
        if (searchMethod == CSV_NUMBER) {
            tripDAO = new TripCsvDAO();
        } else if (searchMethod == JSON_NUMBER) {
            tripDAO = new TripJsonDAO();
        }
    }

//    public Trips getTrips() {
//        return tripDAO.selectTripList();
//    }

    public List<Trip> getTrips() {
        return tripDAO.selectTripList();
    }

//    public boolean isTripsEmpty() {
//        System.out.println("isTripsEmpty(): " + tripDAO.countTripFiles());
//        if (tripDAO.countTripFiles() == 0) return true;
//        return false;
//    }

    public Optional<Trip> getTripBySearchId(int searchId) {
        Optional<Trip> trip = Optional.ofNullable(tripDAO.selectTrip(searchId));
        return trip;
    }

    public Optional<Itineraries> getItinerariesByTrip(int tripId) {
        Optional<Trip> trip = Optional.ofNullable(tripDAO.selectTrip(tripId));
        Optional<Itineraries> itineraries = Optional.ofNullable(trip.get().getItineraries());

        return itineraries;

    }
//    public List<Itinerary> selectItierariesByTrip(int tripId) {
//        List<Trip> trips = getTrips();
//
//    }

    public Optional<Itinerary> getItineraryBySearchId(int tripId, int itId) {
        Optional<Itinerary> itinerary = Optional.ofNullable(tripDAO.selectItinerary(tripId,itId));
        return itinerary;
    }

}
