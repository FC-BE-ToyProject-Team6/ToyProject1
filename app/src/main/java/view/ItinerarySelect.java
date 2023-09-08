package view;


import static common.StringUtil.*;
import common.Scan;
import controller.ItinerarySelectController;
import java.util.List;
import java.util.Optional;
import model.Itineraries;
import model.Itinerary;
import model.Trip;


public class ItinerarySelect implements ConsoleView {
    private static ItinerarySelect instance;
    private static ItinerarySelectController isController;
    private int searchTripId, searchItId;

    public static ItinerarySelect getInstance() {
        if (instance == null) instance = new ItinerarySelect();
        return instance;
    }

    public ItinerarySelect() {
        isController = new ItinerarySelectController();
    }

    @Override
    public ConsoleView print() {
        printTitle("여정 조회");

        /**
         * 1. 여행 리스트 print
         * 2. 찾고 싶은 여행 ID 입력.
         * 3. 찾고 싶은 여행 정보 출력.
         * 4. 찾고 싶은 여정 ID 입력.
         * 5. 찾고 싶은 여정 정보 출력.
         */

        /** 만약 여행이 없다면 -> MainMenu 이동 **/
        if (!printTripList()) {
            return new MainMenu();
        }

        searchTripId = askIdToSelectTrip();
        printTripBySearchTripId();

        searchItId = askIdToSelectItinerary();
        printItinerariesBySearchItId();

        return new MainMenu();
    }


    private boolean printTripList() {
        Optional<List<Trip>> optional = isController.getTrips();
        if (!printEmpty(optional, "여행")) return false;
        return printTripsTable(optional.get());

    }

    private int askIdToSelectTrip() {
        int ans = Scan.nextInt("\nQ. 조회할 여행의 아이디를 입력하세요");
        return ans;
    }

    private void printTripBySearchTripId() {
        Optional<Trip> optional;
        while (!printQuestionIDAgain(optional = isController.getTripBySearchId(searchTripId))) {
            searchTripId = askIdToSelectTrip();
        }

        Trip trip = optional.get();
        Optional<Itineraries> optionalIt = isController.getItinerariesByTrip(trip.getTripId());
        if (!printEmpty(optional, "여정")) return;
        printItinerariesSummary(trip.getTripName(), optionalIt.get());

    }

    private int askIdToSelectItinerary() {
        int ans = Scan.nextInt("\nQ. 조회할 여정의 아이디를 입력하세요");
        return ans;
    }

    private void printItinerariesBySearchItId() {
        Optional<Itinerary> optional;
        while (!printQuestionIDAgain(optional = isController.getItineraryBySearchId(searchTripId, searchItId))) {
            searchItId = askIdToSelectItinerary();
        }

        printItinerary(optional.get());
    }


}
