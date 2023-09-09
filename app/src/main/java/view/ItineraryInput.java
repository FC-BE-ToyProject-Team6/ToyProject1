package view;


import static common.StringUtil.*;

import common.Scan;
import controller.ItineraryInputController;
import java.util.List;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.dao.TripJsonDAO;


public class ItineraryInput implements ConsoleView {

    private final ItineraryInputController itController;
    private int searchTripId;


    public ItineraryInput() {
        itController = new ItineraryInputController();
    }

    @Override
    public ConsoleView print() {

        printTitle("여정 기록");

        if (displayTrips()) {
            inputItinerary();
        }  else {
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

