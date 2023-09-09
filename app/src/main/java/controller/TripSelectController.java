package controller;

import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.Trips;
import model.dao.TripDAO;
import model.dao.TripJsonDAO;

import java.util.Optional;

public class TripSelectController {
    private TripDAO tripDAO;

    public TripSelectController() {
        this.tripDAO = new TripJsonDAO();
    }

    public Optional<Trips> getAllTrips() {
        Trips trips = tripDAO.selectTripList();
        if (trips.getTrips().isEmpty()) return Optional.empty();
        return Optional.of(trips);
    }

    public Optional<Trip> getTripBySearchId(int searchId) {
        Optional<Trip> trip;
        trip = Optional.ofNullable(tripDAO.selectTrip(searchId));

        return trip;
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
