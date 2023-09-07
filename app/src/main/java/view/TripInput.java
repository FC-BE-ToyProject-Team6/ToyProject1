package view;

import controller.TripInputController;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.TripDto;

public class TripInput implements ConsoleView {

    private final Scanner scanner;

    private final TripInputController controller;

    public TripInput() {
        scanner = ConsoleUtil.getScanner();
        controller = new TripInputController();
    }

    @Override
    public ConsoleView print() {
        ConsoleUtil.printTitle("여행 기록");

        int tripId = inputTrip();
        inputItinerary(tripId);

        return new MainMenu();
    }

    public int inputTrip() {
        ConsoleUtil.printQuestion("여행 정보를 입력 해주세요.");
        ConsoleUtil.inputValue("여행 이름");
        String tripName = scanner.nextLine();

        ConsoleUtil.inputValue("시작 날짜(yyyy-mm-dd)");
        String startDate = scanner.nextLine();

        ConsoleUtil.inputValue("종료 날짜(yyyy-mm-dd)");
        String endDate = scanner.nextLine();

        TripDto dto = new TripDto(tripName, startDate, endDate);

        int tripId = controller.createTrip(dto);
        System.out.println("여행 정보가 저장 되었습니다.");
        return tripId;
    }

    public void inputItinerary(int TripId) {
        Itineraries itineraries = new Itineraries();

        while (true) {
            ConsoleUtil.printQuestion("여정 정보를 입력해주세요.");

            ConsoleUtil.inputValue("출발지");
            String departurePlace = scanner.nextLine();

            ConsoleUtil.inputValue("도착지");
            String destination = scanner.nextLine();

            ConsoleUtil.inputValue("출발 시간(yyyy-mm-dd hh:mi)");
            String departureTime = scanner.nextLine();

            ConsoleUtil.inputValue("도착 시간(yyyy-mm-dd hh:mi)");
            String arrivalTime = scanner.nextLine();

            ConsoleUtil.inputValue("체크 인(yyyy-mm-dd hh:mi)");
            String checkIn = scanner.nextLine();

            ConsoleUtil.inputValue("체크 아웃(yyyy-mm-dd hh:mi)");
            String checkOut = scanner.nextLine();

            itineraries.add(
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                )
            );

            System.out.println();
            ConsoleUtil.inputValue("Q. 다음 여정을 입력 하시겠습니까?(Y/N) : ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equalsIgnoreCase("N")) {
                break;
            }
        }
        controller.insertItineraries(TripId, itineraries);
    }
}
