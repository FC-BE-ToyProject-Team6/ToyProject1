package common;

import java.util.List;
import java.util.Scanner;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.Trips;

public class StringUtil {

    private static final Scanner sc;

    static {
        sc = new Scanner(System.in);
    }

    public static Scanner getScanner() {
        return sc;
    }

    public static void printDivier() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append("----------");
        }
        System.out.println(sb);
    }

    public static void printTitle(String str) {
        System.out.println();
        printDivier();
        System.out.println("\t# " + str + " #");
        printDivier();
    }

    public static void printQuestion(String str) {
        System.out.println("\nQ. " + str);
    }

    public static void inputValue(String str) {
        System.out.print(str + " : ");
    }

    public static void println(String str) {
        System.out.println(str);
    }


    public static boolean printTripsTable(Trips trips, String title) {
        if (title.isEmpty()) {
            System.out.println("# 여행 정보 #");
        } else {
            System.out.println("# " + title + " #");
        }

        if (trips == null || trips.isEmpty()) {
            System.out.println("여행 정보가 없습니다.");
            return false;
        }

        System.out.printf("%4s %-20s  | %-8s | %-8s |\n", "ID", "\t여행이름", "출발일", "도착일");

        for (Trip trip : trips.getTrips()) {
            System.out.printf("%4d  %-20s  | %10s | %10s |\n", trip.getTripId(), trip.getTripName(),
                trip.getStartDate(), trip.getEndDate());
        }
        return true;
    }

    public static boolean printTripsTable(Trips trips) {
        return printTripsTable(trips, "");
    }

    public static boolean printTripsTable(List<Trip> trips) {
        return printTripsTable(new Trips(trips));
    }

    public static boolean printItinerariesTable(Itineraries itineraries) {

        System.out.println("\n# 여정 목록 #");

        if (itineraries == null || itineraries.isEmpty()) {
            System.out.println("여정 정보가 없습니다.");
            return false;
        }

        System.out.printf("%-4s | %-5s | %-10s | %-12s | %-12s | %-12s | %-12s |\n", "ID", "출발지",
            "도착지", "출발시간", "도착시간", "체크인", "체크아웃");

        for (Itinerary itinerary : itineraries.getList()) {
            System.out.printf("%-4d | %-5s | %-10s | %-15s | %-15s | %-15s | %-15s |\n",
                itinerary.getItineraryId(), itinerary.getDeparturePlace(),
                itinerary.getDestination(), itinerary.getDepartureTime(),
                itinerary.getArrivalTime(), itinerary.getCheckIn(), itinerary.getCheckOut());
        }
        return true;
    }


}
