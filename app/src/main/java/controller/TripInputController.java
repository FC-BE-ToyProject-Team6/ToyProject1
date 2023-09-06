package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import model.Date;
import model.DateTime;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.dao.TripCsvDAO;
import model.dao.TripJsonDAO;
import view.TripInputView;

public class TripInputController {

    private TripInputView view;
    private TripCsvDAO csvDAO;
    private TripJsonDAO jsonDAO;

    public TripInputController() {
        view = new TripInputView();
        csvDAO = new TripCsvDAO();
        jsonDAO = new TripJsonDAO();
    }

    public void createTrip() {
        csvDAO.createTrip(view.inputTrip());
        jsonDAO.createTrip(view.inputTrip());
    }


}
