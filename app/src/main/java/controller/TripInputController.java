package controller;

import java.util.List;
import model.itinerary.ItineraryDto;
import model.trip.TripDto;
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

    public void insertItineraries(int tripId, List<ItineraryDto> itineraries) {
        for (ItineraryDto itinerary : itineraries) {
            csvDAO.insertItinerary(tripId, itinerary);
            jsonDAO.insertItinerary(tripId, itinerary);
        }
    }

}
