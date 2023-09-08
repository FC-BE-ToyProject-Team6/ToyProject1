package view;


import static common.StringUtil.*;

import controller.ItineraryInputController;
import java.util.List;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.dao.TripJsonDAO;


public class ItineraryInput implements ConsoleView {

    private final Scanner scanner;
    private final ItineraryInputController controller;


    public ItineraryInput() {
        scanner = getScanner();
        controller = new ItineraryInputController();

    }


    @Override
    public ConsoleView print() {

        printTitle("여정 기록");

        inputItinerary();
        return new MainMenu();
    }

    public void displayTrips() {
        List<Trip> trips = controller.getTrips();
        printTripsTable(trips);
        /** 예외 : 만약 생성된 여행 정보가 없다면, 여정 기록은 종료.*/
//        if (trips == null || trips.isEmpty()) {
//            ConsoleUtil.print("현재 기록된 여행 정보가 없습니다. 먼저 여행을 기록해주세요.");
//            return;
//        }
//        Collections.sort(trips, new Comparator<Trip>() {
//            @Override
//            public int compare(Trip trip1, Trip trip2) {
//                return Integer.compare(trip1.getTripId(), trip2.getTripId());
//            }
//        });
//        ConsoleUtil.print("여행목록");
//        System.out.println("ID | 여행이름");
//        for (Trip trip : trips) {
//            System.out.println(trip.getTripId() + "  | " + trip.getTripName());
//        }
//        System.out.println();
    }

    public void displayTripsItinerary(int tripId) {
        Trip selectedTrip = controller.selectTrip(tripId);
        Itineraries itineraries = selectedTrip.getItineraries();
        if (selectedTrip != null) {
            printDivier();
            println("# 선택한 여행 상세 정보 #");
            println("ID \t: " + selectedTrip.getTripId());
            println("여행이름\t: " + selectedTrip.getTripName());
            println("출발일\t: " + selectedTrip.getStartDate());
            println("도착일\t: " + selectedTrip.getEndDate());
//            System.out.printf("여행 이름: %s, 출발일: %s, 도착일: %s\n",
//                selectedTrip.getTripName(),
//                selectedTrip.getStartDate(),
//                selectedTrip.getEndDate()
//            );

            printItinerariesTable(itineraries);

//            System.out.println();
//            System.out.println("                                     [ 여정 목록 ]               ");
//            System.out.println(
//                "ID   출발지   도착지             출발시간           도착시간          체크인            체크아웃");
//            List<Itinerary> itineraryList = itineraries.getList();
//
//            int id = 1;
//            for (Itinerary it : itineraryList) {
//                System.out.printf("%-5d %-5s %-10s %-15s %-15s %-15s %-40s\n",
//                    id,
//                    it.getDeparturePlace(),
//                    it.getDestination(),
//                    it.getDepartureTime(),
//                    it.getArrivalTime(),
//                    it.getCheckIn(),
//                    it.getCheckOut());
//                id++;
//            }
//            System.out.println();
        }
    }


    public Itinerary inputItinerary() {
        displayTrips();

        int tripId = -1;

        while (true) {
            inputValue("Q. 작성하실 여행의 ID를 입력해주세요 ");
            tripId = scanner.nextInt();
            scanner.nextLine();

            Trip selectedTrip = controller.selectTrip(tripId);

            if (selectedTrip == null) {
                System.out.println("존재하지 않는 id입니다. 다시 입력해주세요.");
            } else {
                displayTripsItinerary(selectedTrip.getTripId());
                break;
            }
        }



        while (true) {
            inputValue("Q. 여정을 추가 하시겠습니까?(Y/N)");
            String choice = scanner.nextLine();
            /** Y/N 유효성 검사 **/
            while (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))) {
                System.out.println("잘못된 입력입니다. Y 또는 N만 입력해주세요.");
                inputValue("Q. 여정을 추가 하시겠습니까?(Y/N)");
                choice = scanner.nextLine();
            }

            if (choice.equalsIgnoreCase("N")) {
                break;
            }

            System.out.println("여정 정보를 입력해 주세요");

            inputValue("출발지");
            String departurePlace = scanner.nextLine();

            inputValue("도착지");
            String destination = scanner.nextLine();

            inputValue("출발시간(yyyy-mm-dd hh:mi)");
            String departureTime = scanner.nextLine();

            inputValue("도착시간(yyyy-mm-dd hh:mi)");
            String arrivalTime = scanner.nextLine();

            inputValue("체크인(yyyy-mm-dd hh:mi)");
            String checkIn = scanner.nextLine();

            inputValue("체크아웃(yyyy-mm-dd hh:mi)");
            String checkOut = scanner.nextLine();

            Itinerary itineraries =
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                );

            controller.addItinerary(itineraries, tripId);
            System.out.println();
            System.out.println("여정 기록이 완료되었습니다!\n");
        }

        return null;
    }

    public boolean wantToContinue() {
        Scanner scanner = new Scanner(System.in);
        inputValue("Q. 여정을 추가 하시겠습니까?(Y/N)");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("Y");
    }


}
