package view;

import static common.StringUtil.*;

import common.Scan;
import java.util.Optional;

import controller.TripSelectController;
import model.Itineraries;
import model.Trip;
import model.Trips;

public class TripsSelect implements ConsoleView {

    private TripSelectController tsController;
    public static TripsSelect instance;
    private static int searchTripId;


    public TripsSelect() {
        this.tsController = new TripSelectController();
    }

    public static TripsSelect getInstance() {
        if (instance == null) instance = new TripsSelect();
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

        return new MainMenu();
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
        if (!printEmpty(optional, TRIP)) return false;
        return printTripsTable(optional.get());
    }

    private boolean printTripBySearchTripId() {
        Optional<Trip> optional;
        while (!printQuestionIDAgain(optional = tsController.getTripBySearchId(searchTripId = askId(TRIP))));

        Trip trip = optional.get();
        Optional<Itineraries> optionalIt = tsController.getItinerariesByTrip(trip.getTripId());
        if (!printEmpty(optionalIt, ITINERARY)) return false;

        printItinerariesSummary(trip.getTripName(), optionalIt.get());
        return true;
    }
}
