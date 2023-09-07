package view;


import controller.ItineraryInputController;
import model.Itineraries;
import model.Itinerary;
import model.Trip;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ItineraryInputView implements ConsoleView {
    private static ItineraryInputController itineraryInputController = new ItineraryInputController();


    public static void displayTrips() {
        List<Trip> trips = itineraryInputController.getTrips();
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return Integer.compare(trip1.getTripId(), trip2.getTripId());
            }
        });
        System.out.println("= 여행목록 =");
        System.out.println("ID | 여행이름");
        for (Trip trip : trips) {
            System.out.println(trip.getTripId() + "  | " + trip.getTripName());
        }
        System.out.println();
    }
    public static void displayTripsItinerary(int tripId) {
        Trip selectedTrip = itineraryInputController.selectTrip(tripId);
        Itineraries itineraries = selectedTrip.getItineraries();
        if (selectedTrip != null) {
            System.out.println("= 선택한 여행정보 =");
            System.out.printf("여행 이름: %s, 출발일: %s, 도착일: %s\n",
                    selectedTrip.getTripName(),
                    selectedTrip.getStartDate(),
                    selectedTrip.getEndDate());

            System.out.println();
            System.out.println("[ 여정 목록 ]");
            System.out.println("ID   출발지   도착지             출발시간           도착시간          체크인            체크아웃");
            List<Itinerary> itineraryList = itineraries.getList();

            int id = 1;
            for (Itinerary it :itineraryList) {
                System.out.printf("%-5d %-5s %-10s %-15s %-15s %-15s %-40s\n",
                        id,
                        it.getDeparturePlace(),
                        it.getDestination(),
                        it.getDepartureTime(),
                        it.getArrivalTime(),
                        it.getCheckIn(),
                        it.getCheckOut());
                id++;
            }
            System.out.println("");
        }
    }


    public static Itinerary inputItinerary(Scanner scanner) {
        displayTrips();

        System.out.print("Q. 작성하실 여행의 ID를 입력해주세요 : ");
        int tripId = scanner.nextInt();
        scanner.nextLine();

        displayTripsItinerary(tripId);

        while (true) {
            System.out.print("Q. 여정을 추가 하시겠습니까?(Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("N")) break;

                System.out.println("여정 정보를 입력해 주세요");

                System.out.print("출발지 : ");
                String departurePlace = scanner.nextLine();

                System.out.print("도착지 : ");
                String destination = scanner.nextLine();

                System.out.print("출발시간(ex. 2023-09-04 20:30) : ");
                String departureTime = scanner.nextLine();

                System.out.print("도착시간(ex. 2023-09-04 20:30) : ");
                String arrivalTime = scanner.nextLine();

                System.out.print("체크인(ex. 2023-09-04 20:30) : ");
                String checkIn = scanner.nextLine();

                System.out.print("체크아웃(ex. 2023-09-04 20:30) : ");
                String checkOut = scanner.nextLine();

                Itinerary itineraries =
                        new Itinerary(
                                departurePlace, destination,
                                departureTime, arrivalTime,
                                checkIn, checkOut
                        );

                itineraryInputController.addItinerary(itineraries, tripId);
            }


        return null;
    }
    public boolean wantToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Q. 여정을 추가 하시겠습니까?(Y/N)");
        String choice = scanner.next();
        return choice.equalsIgnoreCase("Y");
    }





    @Override
    public void print() {

    }
}

