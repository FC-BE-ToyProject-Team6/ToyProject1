package view;


import common.StringUtil;
import controller.ItinerarySelectController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import model.Itineraries;
import model.Itinerary;
import model.Trip;


public class ItinerarySelect implements ConsoleView {

    private static final int CSV_NUMBER = 1, JSON_NUMBER = 2;
    private static BufferedReader br;
    private static ItinerarySelectController itinerarySelectController;
    //    private Trips trips;
//    private List<Trip> trips;
    private Itineraries itineraries;
    private int searchTripId, searchItId;


    public ItinerarySelect() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public ConsoleView print() {

        StringUtil.printTitle("여정 조회");

        /**
         * 1. csv OR json 선택.
         * 2. 여행 리스트 print
         * 3. 찾고 싶은 여행 ID 입력.
         * 4. 찾고 싶은 여행 정보 출력.
         * 5. 찾고 싶은 여정 ID 입력.
         * 6. 찾고 싶은 여정 정보 출력.
         * 7. BufferedReader close.
         */

        askToSelectCSVOrJSON();
        // TODO 만약 여행이 없다면 -> TripInputView 이동
        if (!printTripList()) {
//            closeBr();
//            ConsoleView moveTripInput = new TripInputView();
//            moveTripInput.print();
//            return;

            // TODO TEST CODE -> 추후 없앨 것. -> Test 해보실 분 사용하세요!

//            String[] travelNames = {"프랑스 여행", "영국 여행", "family vacation", "!@도쿄여행!@&^", "베트남 하노이 gogo"};
//            for (int i = 0; i < 5; i++) {
//                Trip newTrip = new Trip(travelNames[i], "2023-04-11", "2023-04-12");
//                Itinerary newIt = new Itinerary("몽쉘미쉘", "에펠탑", "2023-04-11 12:00",
//                        "2023-04-12 10:00","2023-04-11 12:00","2023-04-12 10:26");
//                Itinerary newIt2 = new Itinerary("에펠탑", "루브르", "2023-04-12 12:00",
//                        "2023-04-12 13:20","2023-04-12 12:00","2023-04-13 10:26");
//                Itinerary newIt3 = new Itinerary("루브르", "오르세 미술관", "2023-04-12 12:00",
//                        "2023-04-12 13:20","2023-04-12 12:00","2023-04-13 10:26");
//                Itinerary newIt4 = new Itinerary("오르세 미술관", "스트라스부르", "2023-04-12 12:00",
//                        "2023-04-12 13:20","2023-04-12 12:00","2023-04-13 10:26");
//
//                Itineraries its = new Itineraries();
//                its.add(newIt);
//                its.add(newIt2);
//                newTrip.setItineraries(its);
//                TripDAO tripDAO = new TripJsonDAO();
//                int trip_id_tmp = tripDAO.createTrip(newTrip);
//
//                tripDAO.insertItinerary(trip_id_tmp, newIt);
//                tripDAO.insertItinerary(trip_id_tmp, newIt2);
//                tripDAO.insertItinerary(trip_id_tmp, newIt3);
//                tripDAO.insertItinerary(trip_id_tmp, newIt4);
//            }
            printTripList();
        }

        searchTripId = askIdToSelectTrip();
        printTripBySearchTripId();

        searchItId = askIdToSelectItinerary();
        printItinerariesBySearchItId();
        //closeBr();

        return new MainMenu();
    }

    private void askToSelectCSVOrJSON() {
        try {
            StringUtil.inputValue("Q. 조회 방식을 선택하세요 (1: CSV, 2: JSON): ");
            String input = br.readLine();

            while (!isCorrectChoice(input)) {
                StringUtil.inputValue("Q. 입력 방식이 틀렸습니다. 다시 조회 방식을 선택하세요 (1: CSV, 2: JSON): ");
                input = br.readLine();
            }
            itinerarySelectController = new ItinerarySelectController(Integer.parseInt(input));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean printTripList() {

        List<Trip> trips = itinerarySelectController.getTrips();
        return StringUtil.printTripsTable(trips);

//        System.out.println("# 여행 목록 #");
//        System.out.println("ID\t| 여행 이름\t\t");
//
//        if (trips.size() == 0) {
//            System.out.println("여행 정보가 없습니다. 여행 입력으로 이동합니다.");
//            return false;
//        }
//
//        for (Trip trip : trips) {
//            System.out.println(trip.getTripId() + "\t| " + trip.getTripName());
//        }
//
//        return true;
    }

    private int askIdToSelectTrip() {
        int ans = 0;
        try {
            StringUtil.inputValue("\nQ. 조회할 여행의 아이디를 입력하세요");
            String input = br.readLine();
            while (!isCorrectAnswer(input)) {
                StringUtil.inputValue("Q. 입력 방식이 틀렸습니다. 조회할 여행의 아이디를 입력하세요");
                input = br.readLine();
            }
            ans = Integer.parseInt(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    private void printTripBySearchTripId() {

        Optional<Trip> optional = itinerarySelectController.getTripBySearchId(searchTripId);
        while (optional.isEmpty()) {
            System.out.println("조회 정보가 없습니다. 다시 여행 아이디 검색을 진행합니다. ");
            searchTripId = askIdToSelectTrip();
            optional = itinerarySelectController.getTripBySearchId(searchTripId);
        }

        Trip trip = optional.get();
        System.out.println("\n[" + trip.getTripName() + "] 여행의 여정 정보입니다.");

        Optional<Itineraries> optionalIt = itinerarySelectController.getItinerariesByTrip(
            trip.getTripId());

        if (optionalIt.isEmpty()) {
            System.out.println("조회된 여정들의 정보가 없습니다. ");
        } else {
            itineraries = optionalIt.get();
            for (Itinerary it : itineraries.getList()) {
                System.out.println(
                    "ID " + it.getItineraryId() + "\t: " + it.getDeparturePlace() + " -> "
                        + it.getDestination());
            }
        }

    }

    private int askIdToSelectItinerary() {
        int ans = 0;
        try {
            StringUtil.inputValue("\nQ. 조회할 여정의 아이디를 입력하세요");
            String input = br.readLine();
            while (!isCorrectAnswer(input)) {
                StringUtil.inputValue("Q. 입력 방식이 틀렸습니다. 조회할 여정의 아이디를 입력해주세요");
                input = br.readLine();
            }
            ans = Integer.parseInt(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    private void printItinerariesBySearchItId() {

        Optional<Itinerary> optional = itinerarySelectController.getItineraryBySearchId(
            searchTripId, searchItId);
        while (optional.isEmpty()) {
            System.out.println("조회 정보가 없습니다. 다시 여정 아이디 검색을 진행합니다.");
            searchItId = askIdToSelectItinerary();
            optional = itinerarySelectController.getItineraryBySearchId(searchTripId, searchItId);
        }

        Itinerary it = optional.get();
        System.out.println(
            "\n[" + it.getDeparturePlace() + " -> " + it.getDestination() + "] 여정의 상세 정보입니다.");
        System.out.println("출발 시간\t: " + it.getDepartureTime());
        System.out.println("도착 시간\t: " + it.getArrivalTime());
        System.out.println("체크 인\t: " + it.getCheckIn());
        System.out.println("체크 아웃\t: " + it.getCheckOut());
        System.out.println();
    }

    private boolean isCorrectAnswer(String input) {
        /**
         * input 형식이 숫자가 맞는지 확인합니다.
         */
        int correctNum;
        try {
            correctNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;

    }

    private boolean isCorrectChoice(String input) {
        if (!isCorrectAnswer(input)) {
            return false;
        }
        int ans = Integer.parseInt(input);
        return ans == CSV_NUMBER || ans == JSON_NUMBER;
    }

    private void closeBr() {
        try {
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
