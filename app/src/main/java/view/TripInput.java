package view;

import static common.StringUtil.*;

import common.Scan;
import controller.TripInputController;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.TripDto;

public class TripInput implements ConsoleView {
    private final TripInputController tiController;
    private static TripInput instance;

    public TripInput() {
        tiController = new TripInputController();
    }

    public static TripInput getInstance() {
        if (instance == null) instance = new TripInput();
        return instance;
    }

    @Override
    public ConsoleView print() {
        printTitle("여행 기록");

        int tripId = inputTrip();
        inputItinerary(tripId);

        return new MainMenu();
    }

    public int inputTrip() {
        printQuestion("여행 정보를 입력 해주세요.");
        String tripName = Scan.nextLine("여행 이름");
        String startDate = Scan.nextDate("시작 날짜(yyyy-mm-dd)");
        String endDate = Scan.nextDate("종료 날짜(yyyy-mm-dd)");
        TripDto dto = new TripDto(tripName, startDate, endDate);

        int tripId = tiController.createTrip(dto);

        println("여행 정보가 저장 되었습니다.");
        return tripId;
    }

    public void inputItinerary(int TripId) {
        Itineraries itineraries = new Itineraries();

        while (true) {
            printQuestion("여정 정보를 입력해주세요.");

            String departurePlace = Scan.nextLine("출발지");
            String destination = Scan.nextLine("도착지");
            String departureTime = Scan.nextDateTime("출발 시간(yyyy-mm-dd hh:mi)");
            String arrivalTime = Scan.nextDateTime("도착 시간(yyyy-mm-dd hh:mi)");
            String checkIn = Scan.nextDateTime("체크 인(yyyy-mm-dd hh:mi)");
            String checkOut = Scan.nextDateTime("체크 아웃(yyyy-mm-dd hh:mi)");

            itineraries.add(
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                )
            );

            System.out.println();
            String input = Scan.nextYN("Q. 다음 여정을 입력 하시겠습니까?(Y/N");
            if (input.equalsIgnoreCase("N")) {
                break;
            }
        }
        tiController.insertItineraries(TripId, itineraries);
    }
}
