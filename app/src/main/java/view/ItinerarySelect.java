package view;


import static common.StringUtil.*;

import java.util.Optional;

import controller.TripSelectController;
import model.Itinerary;


public class ItinerarySelect implements ConsoleView {
    private static ItinerarySelect instance;
    private static TripSelectController tsController;
    private int searchTripId, searchItId;

    public static ItinerarySelect getInstance() {
        if (instance == null) instance = new ItinerarySelect();
        return instance;
    }

    public ItinerarySelect() {
        tsController = new TripSelectController();
    }

    @Override
    public ConsoleView print() {
        printTitle("여정 조회");

        if (displayTrips()) {
            printItinerariesBySearchItId();
        } else {
            return TripInput.getInstance();
        }

        return new MainMenu();
    }
    public boolean displayTrips() {
        TripsSelect tripsSelect = TripsSelect.getInstance();
        if (!tripsSelect.printByOtherMenu()) return false;
        searchTripId = tripsSelect.getSearchTripId();
        return true;
    }

    private void printItinerariesBySearchItId() {
        Optional<Itinerary> optional;
        while (!printQuestionIDAgain(optional = tsController.getItineraryBySearchId(searchTripId, searchItId = askId(ITINERARY))));

        printItinerary(optional.get());
    }

}
