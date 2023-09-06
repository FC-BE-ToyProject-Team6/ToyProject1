package controller;

import java.math.BigInteger;
import java.util.UUID;
import model.DateTime;
import model.Itinerary;
import model.dao.TripCsvDAO;
import model.dao.TripJsonDAO;
import view.TripInputView;

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
    public void addItinerary(int tripId){
        for (Itinerary itinerary: view.inputItinerary()) {
            csvDAO.insertItinerary(tripId, itinerary);
            jsonDAO.insertItinerary(tripId, itinerary);
        }


    }}
