package view;


import common.Scan;
import controller.TripInputController;
import java.util.ArrayList;
import java.util.List;
import model.DateTime;
import model.itinerary.Itinerary;
import model.itinerary.ItineraryDto;

import static common.StringUtil.*;


public class ItineraryInput implements ConsoleView {

    private static ConsoleView instance;
    private final TripInputController tiController;
    private int searchTripId;


    private ItineraryInput() {
        tiController = new TripInputController();
    }

    public static ConsoleView getInstance() {
        if (instance == null) {
            instance = new ItineraryInput();
        }
        return instance;
    }

    @Override
    public ConsoleView print() {

        printTitle("여정 기록");

        if (displayTrips()) {
            inputItinerary();
        } else {
            return TripInput.getInstance();
        }

        return MainMenu.getInstance();
    }

    public void inputItineraryByOtherView(int id) {
        searchTripId = id;
        inputItinerary();
    }

    public boolean displayTrips() {
        TripsSelect tripsSelect = TripsSelect.getInstance();
        if (!tripsSelect.printByOtherMenu()) {
            return false;
        }
        searchTripId = tripsSelect.getSearchTripId();
        return true;
    }

    public Itinerary inputItinerary() {
        List<ItineraryDto> itineraryDtoList = new ArrayList<>();

        do {
            println("여정 정보를 입력해 주세요");

            String departurePlace = Scan.nextLine("출발지");
            String destination = Scan.nextLine("도착지");
            DateTime departureTime = Scan.nextDateTime("출발시간(yyyy-mm-dd hh:mi)");
            DateTime arrivalTime = Scan.nextDateTime("도착시간(yyyy-mm-dd hh:mi)");
            DateTime checkIn = Scan.nextDateTime("체크인(yyyy-mm-dd hh:mi)");
            DateTime checkOut = Scan.nextDateTime("체크아웃(yyyy-mm-dd hh:mi)");

            ItineraryDto  itinerary =
                    new ItineraryDto(
                            departurePlace, destination,
                            departureTime, arrivalTime,
                            checkIn, checkOut
                    );
            itineraryDtoList.add(itinerary);

            println("");
            println("여정 기록이 완료되었습니다!");
        } while (!(Scan.nextYN("\nQ. 여정을 추가 하시겠습니까?(Y/N)")).equals(N));

        tiController.insertItineraries(searchTripId, itineraryDtoList);

        return null;
    }

}

