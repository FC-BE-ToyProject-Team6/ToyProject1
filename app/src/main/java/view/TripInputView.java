package view;

import controller.ItineraryInputController;
import controller.TripInputController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Date;
import model.DateTime;
import model.Itineraries;
import model.Itinerary;
import model.Trip;

public class TripInputView implements ConsoleView{
    private Scanner scanner;
    private TripInputController tripController;
    private ItineraryInputController itineraryController;

    //지금은 view랑 controller가 1대1 관계라 매개변수 안 넣음
    public TripInputView(){
        scanner = new Scanner(System.in);
        tripController = new TripInputController();
        itineraryController = new ItineraryInputController();
    }

    @Override
    public void print() {
        System.out.println("여행 정보를 입력해주세요.");
        inputTrip();
        System.out.println("여행 정보가 저장됐습니다.");

        inputItinerary();

    }

    public Trip inputTrip() {
        System.out.println("여행 이름: ");
        String tripName = scanner.nextLine();

        System.out.println("시작 날짜: ");
        String startDate = scanner.nextLine();

        System.out.println("종료 날짜: ");
        String endDate = scanner.nextLine();

        scanner.close();

        return new Trip(
            tripName,
            startDate,
            endDate
        );
    }

    public List<Itinerary> inputItinerary(){
        List<Itinerary> itineraries = new ArrayList<>();

        while (true){
            System.out.println("여정 정보를 입력해주세요.");

            System.out.println("출발지: ");
            String departurePlace = scanner.nextLine();

            System.out.println("도착지: ");
            String destination = scanner.nextLine();

            System.out.println("출발 시간: ");
            String departureTime = scanner.nextLine();

            System.out.println("도착 시간: ");
            String arrivalTime = scanner.nextLine();

            System.out.println("체크 인: ");
            String checkIn = scanner.nextLine();

            System.out.println("체크 아웃: ");
            String checkOut = scanner.nextLine();

            itineraries.add(
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                )
            );

            System.out.println("Q. 다음 여정을 입력 하시겠습니까?(Y/N) : ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("N")) break;
            //반복문 전 개행 문자 제거
            scanner.nextLine();
        }
        scanner.close();
        return itineraries;
    }
}
