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
    public void createItinerary() {
        for (Itinerary itinerary: view.inputItinerary()) {
            csvDAO.insertItinerary(itinerary);
            jsonDAO.insertItinerary(itinerary);
        }
    }

}
