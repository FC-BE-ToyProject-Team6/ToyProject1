package view;

import static common.StringUtil.*;
import java.util.List;
import java.util.Scanner;
import model.Trip;
import model.Trips;
import model.dao.TripCsvDAO;
import model.dao.TripDAO;
import model.dao.TripJsonDAO;

public class TripsSelect implements ConsoleView {

    private final Scanner scanner;
    private TripDAO tripDAO;


    public TripsSelect(TripDAO tripDAO) {
        scanner = getScanner();
        this.tripDAO = tripDAO;
    }

    @Override
    public ConsoleView print() {

        printTitle("여행조회");

        inputValue("Q : 조회 방식을 선택하세요 (1 : CSV, 2 : JSON)");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            tripDAO = new TripCsvDAO();
        } else if (choice == 2) {
            tripDAO = new TripJsonDAO();
        } else {
            System.out.println("잘못된 선택입니다.");
            return new MainMenu();
        }

        // 여행 목록 출력
        List<Trip> tripList = tripDAO.selectTripList();
        if (tripList.isEmpty()) {
            println("여행 정보가 없습니다.");
            return new MainMenu();
        }

        printTripsTable(tripList);

//        System.out.println("여행 목록");
//        System.out.println("-----------------------------------");
//        System.out.println("ID | 여행이름    | 출발일 | 도착일 |");
//
//
//        if (tripList.isEmpty()) {
//            System.out.println("여행을 찾을 수 없습니다.");
//        } else {
//            for (Trip trip : tripList) {
//                System.out.printf("%d.   %s  | %s| %s|\n",
//                    trip.getTripId(),
//                    trip.getTripName(),
//                    trip.getStartDate(),
//                    trip.getEndDate());
//            }
//        }

        // 사용자로부터 조회할 여행의 ID 입력 받기
        System.out.print("조회할 여행의 ID를 입력해 주세요: ");
        int tripId = Integer.parseInt(scanner.nextLine());

        // 선택한 여행 정보 출력
        Trip selectedTrip = tripDAO.selectTrip(tripId);

        if (selectedTrip != null) {
            Trips trips = new Trips();
            trips.addTrip(selectedTrip);
            printTripsTable(trips, "선택한 여행 정보");
//            System.out.println("= 선택한 여행 정보 =");
//
//            System.out.print(selectedTrip.getTripName());
//            System.out.print(", 출발일 : " + selectedTrip.getStartDate());
//            System.out.print(" 도착일 : " + selectedTrip.getEndDate());
        } else {
            System.out.println("해당 ID로 여행을 찾을 수 없습니다.");
        }

        return new MainMenu();
    }
}
