package view;


import static common.StringUtil.ITINERARY;
import static common.StringUtil.askId;
import static common.StringUtil.printItinerary;
import static common.StringUtil.printQuestionIDAgain;
import static common.StringUtil.printTitle;

import controller.TripSelectController;
import java.util.Optional;
import model.Itinerary;


public class ItinerarySelect implements ConsoleView {

    private static ItinerarySelect instance;
    private static TripSelectController tsController;
    private int searchTripId, searchItId;

    private ItinerarySelect() {
        tsController = new TripSelectController();
    }

    public static ItinerarySelect getInstance() {
        if (instance == null) {
            instance = new ItinerarySelect();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {
        printTitle("여정 조회");

        if (displayTrips()) {
            printItinerariesBySearchItId();
        } else {
            return TripInput.getInstance();
        }

        return MainMenu.getInstance();
    }

    public boolean displayTrips() {
        TripsSelect tripsSelect = TripsSelect.getInstance();
        if (!tripsSelect.printByOtherMenu()) {
            return false;
        }
        searchTripId = tripsSelect.getSearchTripId();
        return true;
    }

    private void printItinerariesBySearchItId() {
        Optional<Itinerary> optional;
        while (!printQuestionIDAgain(optional = tsController.getItineraryBySearchId(searchTripId,
            searchItId = askId(ITINERARY))))
            ;

        printItinerary(optional.get());
    }

}
