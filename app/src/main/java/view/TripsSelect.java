package view;

import common.Scan;
import controller.TripSelectController;
import java.util.Optional;
import model.itinerary.Itineraries;
import model.trip.Trip;
import model.trip.Trips;

import static common.StringUtil.*;

public class TripsSelect implements ConsoleView {

    private static final int CSV = 1, JSON = 2;
    private static TripsSelect instance;
    private static int searchTripId;
    private final TripSelectController tsController;
    private int readMethod = JSON;



    private TripsSelect() {
        askReadMethod();
        this.tsController = new TripSelectController(readMethod);
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

    private void askReadMethod() {
        readMethod = Scan.nextInt("\nQ. 조회 방법을 선택합니다. 한번 입력하면 다시는 수정할 수 없습니다.(1 : CSV, 2: JSON)", CSV, JSON);
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
