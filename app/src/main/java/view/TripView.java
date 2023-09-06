package view;

import model.Trip;
import model.dao.TripCsvDAO;
import model.dao.TripDAO;

import java.util.List;
import java.util.Scanner;
import model.dao.TripJsonDAO;

public class TripView implements ConsoleView {
    private TripDAO tripDAO;

    public TripView(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    @Override
    public void print() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose data storage format:");
        System.out.println("1. CSV");
        System.out.println("2. JSON");
        int choice = scanner.nextInt();

        if (choice == 1) {
            tripDAO = new TripCsvDAO();
        } else if (choice == 2) {
            tripDAO = new TripJsonDAO();
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        // 여행 목록 출력
        System.out.println("여행 목록");
        System.out.println("-----------------------------------");
        System.out.println("ID | 여행이름    | 출발일 | 도착일 |");

        List<Trip> tripList = tripDAO.selectTripList();

        if (tripList.isEmpty()) {
            System.out.println("No trips found.");
        } else {
            for (Trip trip : tripList) {
                System.out.printf("%d.   %s  | %s| %s|\n",
                    trip.getTripId(),
                    trip.getTripName(),
                    trip.getStartDate(),
                    trip.getEndDate());
            }
        }

        // 사용자로부터 조회할 여행의 ID 입력 받기
        System.out.print("조회할 여행의 ID를 입력해 주세요: ");
        int tripId = scanner.nextInt();

        // 선택한 여행 정보 출력
        Trip selectedTrip = tripDAO.selectTrip(tripId);

        if (selectedTrip != null) {
            System.out.println("= 선택한 여행 정보 =");

            System.out.print( selectedTrip.getTripName());
            System.out.print(", 출발일 : " + selectedTrip.getStartDate());
            System.out.print(" 도착일 : " + selectedTrip.getEndDate());
        } else {
            System.out.println("해당 ID로 여행을 찾을 수 없습니다.");
        }
    }
}
