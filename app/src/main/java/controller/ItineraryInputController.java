package controller;

import java.util.List;
import model.Itinerary;
import model.Trip;
import model.dao.TripCsvDAO;
import model.dao.TripJsonDAO;

public class ItineraryInputController {

    private final TripCsvDAO csvDAO;
    private final TripJsonDAO jsonDAO;

    public ItineraryInputController() {
        csvDAO = new TripCsvDAO();
        jsonDAO = new TripJsonDAO();
    }
    //처음에 여행 생성시 여정 기록화면
//    public void createItinerary() {
//        int tripId = jsonDAO.getLastTripId();
//        for (Itinerary itinerary: view.inputItinerary().getList()) {
//            csvDAO.insertItinerary(tripId, itinerary);
//            jsonDAO.insertItinerary(tripId, itinerary);
//        }
//    }

    // 여정 기록 화면
    public void addItinerary(Itinerary itinerary, int tripId) {
        csvDAO.insertItinerary(tripId, itinerary);
        jsonDAO.insertItinerary(tripId, itinerary);
    }

    public List<Trip> getTrips() {
        return jsonDAO.selectTripList();
    }

    public Trip selectTrip(int tripId) {
        return jsonDAO.selectTrip(tripId);
    }


}
