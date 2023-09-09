package view;

import static common.StringUtil.ITINERARY;
import static common.StringUtil.TRIP;
import static common.StringUtil.askId;
import static common.StringUtil.printEmpty;
import static common.StringUtil.printItinerariesSummary;
import static common.StringUtil.printQuestionIDAgain;
import static common.StringUtil.printTitle;
import static common.StringUtil.printTripsTable;

import controller.TripSelectController;
import java.util.Optional;
import model.Itineraries;
import model.Trip;
import model.Trips;

public class TripsSelect implements ConsoleView {

    private static TripsSelect instance;
    private static int searchTripId;
    private final TripSelectController tsController;


    private TripsSelect() {
        this.tsController = new TripSelectController();
    }

    public static TripsSelect getInstance() {
        if (instance == null) {
            instance = new TripsSelect();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {

        printTitle("여행조회");

        // 여행 목록 출력
        if (printTripList()) {
            printTripBySearchTripId();
        } else {
            return TripInput.getInstance();
        }

        return MainMenu.getInstance();
    }

    public boolean printByOtherMenu() {
        if (printTripList()) {
            printTripBySearchTripId();
            return true;
        }
        return false;
    }

    public int getSearchTripId() {
        return searchTripId;
    }

    private boolean printTripList() {
        Optional<Trips> optional = tsController.getAllTrips();
        if (!printEmpty(optional, TRIP)) {
            return false;
        }
        return printTripsTable(optional.get());
    }

    private boolean printTripBySearchTripId() {
        Optional<Trip> optional;
        while (!printQuestionIDAgain(
            optional = tsController.getTripBySearchId(searchTripId = askId(TRIP))))
            ;

        Trip trip = optional.get();
        Optional<Itineraries> optionalIt = tsController.getItinerariesByTrip(trip.getTripId());
        if (!printEmpty(optionalIt, ITINERARY)) {
            return false;
        }

        printItinerariesSummary(trip.getTripName(), optionalIt.get());
        return true;
    }
}
