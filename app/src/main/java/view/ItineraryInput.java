package view;


import static common.StringUtil.printTitle;
import static common.StringUtil.println;

import common.Scan;
import controller.ItineraryInputController;
import model.Itinerary;


public class ItineraryInput implements ConsoleView {

    private static ConsoleView instance;
    private final ItineraryInputController itController;
    private int searchTripId;


    private ItineraryInput() {
        itController = new ItineraryInputController();
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

    public boolean displayTrips() {
        TripsSelect tripsSelect = TripsSelect.getInstance();
        if (!tripsSelect.printByOtherMenu()) {
            return false;
        }
        searchTripId = tripsSelect.getSearchTripId();
        return true;
    }

    public Itinerary inputItinerary() {

        while (!(Scan.nextYN("\nQ. 여정을 추가 하시겠습니까?(Y/N)")).equals("N")) {

            println("여정 정보를 입력해 주세요");

            String departurePlace = Scan.nextLine("출발지");
            String destination = Scan.nextLine("도착지");
            String departureTime = Scan.nextDateTime("출발시간(yyyy-mm-dd hh:mi)");
            String arrivalTime = Scan.nextDateTime("도착시간(yyyy-mm-dd hh:mi)");
            String checkIn = Scan.nextDateTime("체크인(yyyy-mm-dd hh:mi)");
            String checkOut = Scan.nextDateTime("체크아웃(yyyy-mm-dd hh:mi)");

            Itinerary itineraries =
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                );

            itController.addItinerary(itineraries, searchTripId);

            println("");
            println("여정 기록이 완료되었습니다!");
        }
        return null;
    }

}

