package controller;

import model.Itineraries;
import model.Itinerary;
import model.TripDto;
import model.dao.TripCsvDAO;
import model.dao.TripJsonDAO;

public class TripInputController {

    private final TripCsvDAO csvDAO;
    private final TripJsonDAO jsonDAO;

    public TripInputController() {
        csvDAO = new TripCsvDAO();
        jsonDAO = new TripJsonDAO();
    }

    public int createTrip(TripDto dto) {
        csvDAO.createTrip(dto);
        return jsonDAO.createTrip(dto);
    }

    public void insertItineraries(int tripId, Itineraries itineraries) {
        for (Itinerary itinerary : itineraries.getList()) {
            csvDAO.insertItinerary(tripId, itinerary);
            jsonDAO.insertItinerary(tripId, itinerary);
        }
    }

}
