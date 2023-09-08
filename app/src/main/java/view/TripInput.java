package view;

import static common.StringUtil.*;
import controller.TripInputController;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.TripDto;

public class TripInput implements ConsoleView {

    private final Scanner scanner;

    private final TripInputController controller;

    public TripInput() {
        scanner = getScanner();
        controller = new TripInputController();
    }

    @Override
    public ConsoleView print() {
        printTitle("여행 기록");

        int tripId = inputTrip();
        inputItinerary(tripId);

        return MainMenu.getInstance();
    }

    public int inputTrip() {
        printQuestion("여행 정보를 입력 해주세요.");
        inputValue("여행 이름");
        String tripName = scanner.nextLine();

        inputValue("시작 날짜(yyyy-mm-dd)");
        String startDate = scanner.nextLine();

        inputValue("종료 날짜(yyyy-mm-dd)");
        String endDate = scanner.nextLine();

        TripDto dto = new TripDto(tripName, startDate, endDate);

        int tripId = controller.createTrip(dto);
        System.out.println("여행 정보가 저장 되었습니다.");
        return tripId;
    }

    public void inputItinerary(int TripId) {
        Itineraries itineraries = new Itineraries();

        while (true) {
            printQuestion("여정 정보를 입력해주세요.");

            inputValue("출발지");
            String departurePlace = scanner.nextLine();

            inputValue("도착지");
            String destination = scanner.nextLine();

            inputValue("출발 시간(yyyy-mm-dd hh:mi)");
            String departureTime = scanner.nextLine();

            inputValue("도착 시간(yyyy-mm-dd hh:mi)");
            String arrivalTime = scanner.nextLine();

            inputValue("체크 인(yyyy-mm-dd hh:mi)");
            String checkIn = scanner.nextLine();

            inputValue("체크 아웃(yyyy-mm-dd hh:mi)");
            String checkOut = scanner.nextLine();

            itineraries.add(
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                )
            );

            System.out.println();
            inputValue("Q. 다음 여정을 입력 하시겠습니까?(Y/N) : ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equalsIgnoreCase("N")) {
                break;
            }
        }
        controller.insertItineraries(TripId, itineraries);
    }
}
