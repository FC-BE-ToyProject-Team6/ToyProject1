//package view;
//
//
//import controller.ItineraryInputController;
//import model.Itinerary;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class ItineraryInputView {
//    private ItineraryInputController itineraryInputController = new ItineraryInputController();
//
//    public Itinerary run() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        // display trips
//        System.out.println("= 여행목록 =");
//        System.out.println("ID | 여행이름");
//        System.out.println("1  | 도쿄여행");
//        System.out.println("2  | 제주도여행");
//        System.out.println("3  | 중국여행");
//
//        System.out.print("Q. 작성하실 여행의 ID를 입력해주세요 : ");
//        int tripID = scanner.nextInt();
//
//        while (true) {
//            System.out.println("Q. 여정을 추가 하시겠습니까?(Y/N)");
//            String choice = scanner.next();
//            if (choice.equalsIgnoreCase("N")) break;
//
//            Itinerary itinerary = new Itinerary();
//
//            System.out.print("출발지 : ");
//            itinerary.setDeparturePlace(scanner.next());
//
//            System.out.print("도착지 : ");
//            itinerary.setDestination(scanner.next());
//
//            System.out.print("출발시간(ex. 2023 09 04 AM9:30) : ");
//            itinerary.setDepartureTime(scanner.next());
//
//            System.out.print("도착시간 : ");
//            itinerary.setArrivalTime(scanner.next());
//
//            System.out.print("체크인 : ");
//            itinerary.setCheckIn(scanner.next());
//
//            System.out.print("체크아웃 : ");
//            itinerary.setCheckOut(scanner.next());
//
//            itineraryInputController.addItinerary(itinerary, tripID);
//        }
//
//        scanner.close();
//        return null;
//    }
//    public boolean wantToContinue() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Q. 여정을 추가 하시겠습니까?(Y/N)");
//        String choice = scanner.next();
//        return choice.equalsIgnoreCase("Y");
//    }
//
//
//}
//
