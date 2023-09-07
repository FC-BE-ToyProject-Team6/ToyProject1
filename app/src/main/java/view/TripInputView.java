package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Itinerary;
import model.Trip;

public class TripInputView implements ConsoleView{
    private Scanner scanner;

    //지금은 view랑 controller가 1대1 관계라 매개변수 안 넣음
    public TripInputView(){
        scanner = new Scanner(System.in);
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("여행 정보를 입력해주세요.");
        inputTrip();
        System.out.println("여행 정보가 저장됐습니다.\n");

        inputItinerary();
        scanner.close();

    }

    public Trip inputTrip() {
        System.out.print("여행 이름: ");
        String tripName = scanner.nextLine();

        System.out.print("시작 날짜: ");
        String startDate = scanner.nextLine();

        System.out.print("종료 날짜: ");
        String endDate = scanner.nextLine();

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

            System.out.print("출발지: ");
            String departurePlace = scanner.nextLine();

            System.out.print("도착지: ");
            String destination = scanner.nextLine();

            System.out.print("출발 시간: ");
            String departureTime = scanner.nextLine();

            System.out.print("도착 시간: ");
            String arrivalTime = scanner.nextLine();

            System.out.print("체크 인: ");
            String checkIn = scanner.nextLine();

            System.out.print("체크 아웃: ");
            String checkOut = scanner.nextLine();

            itineraries.add(
                new Itinerary(
                    departurePlace, destination,
                    departureTime, arrivalTime,
                    checkIn, checkOut
                )
            );

            System.out.println();
            System.out.print("Q. 다음 여정을 입력 하시겠습니까?(Y/N 후 엔터 입력) : ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("N")) break;
            //반복문 전 개행 문자 제거
            scanner.nextLine();
        }
        return itineraries;
    }
}
