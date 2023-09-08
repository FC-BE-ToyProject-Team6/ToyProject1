package controller;

import model.*;

import java.util.List;
import java.util.Optional;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.dao.TripCsvDAO;
import model.dao.TripDAO;
import model.dao.TripJsonDAO;

public class ItinerarySelectController {

    private static TripDAO tripDAO;
    private Trips trips = null;

    public ItinerarySelectController() {
        tripDAO = new TripJsonDAO();
    }

//    public Trips getTrips() {
//        return tripDAO.selectTripList();
//    }

    public Optional<List<Trip>> getTrips() {
        List<Trip> tripList = tripDAO.selectTripList();
        if (tripList.size() == 0) return Optional.empty();
        return Optional.of(tripList);
    }

    public Optional<Trip> getTripBySearchId(int searchId) {
        return Optional.ofNullable(tripDAO.selectTrip(searchId));
    }

    public Optional<Itineraries> getItinerariesByTrip(int tripId) {
        Optional<Trip> trip = getTripBySearchId(tripId);
        Itineraries itineraries = trip.get().getItineraries();
        if (itineraries.size() == 0) return Optional.empty();
        return Optional.ofNullable(trip.get().getItineraries());
    }

    public Optional<Itinerary> getItineraryBySearchId(int tripId, int itId) {
        return Optional.ofNullable(tripDAO.selectItinerary(tripId,itId));
    }

}
