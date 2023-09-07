package controller;

import model.Itinerary;
import model.Trip;
import model.dao.TripCsvDAO;
import model.dao.TripJsonDAO;
import view.TripInputView;

import java.util.List;

public class ItineraryInputController {

    private TripInputView view;
    private TripCsvDAO csvDAO;
    private TripJsonDAO jsonDAO;

    public ItineraryInputController() {
        view = new TripInputView();
        csvDAO = new TripCsvDAO();
        jsonDAO = new TripJsonDAO();
    }
    //처음에 여행 생성시 여정 기록화면
    public void createItinerary() {
        int tripId = jsonDAO.getLastTripId();
        for (Itinerary itinerary: view.inputItinerary()) {
            csvDAO.insertItinerary(tripId, itinerary);
            jsonDAO.insertItinerary(tripId, itinerary);
        }
    }

    // 여정 기록 화면
    public void addItinerary(Itinerary itinerary, int tripId){
//        csvDAO.insertItinerary(tripId, itinerary);
        jsonDAO.insertItinerary(tripId, itinerary);
    }
    public List<Trip> getTrips() {
        return jsonDAO.selectTripList();

        }
    public Trip selectTrip(int tripId) {
        return jsonDAO.selectTrip(tripId);
    }


}
