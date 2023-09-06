package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import model.Itinerary;
import model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

class TripJsonDAOTest {
    private TripJsonDAO tripJsonDAO;
    private final String tripFilesFolder = "./trip_json_files/";

    @BeforeEach
    void setUp() {
        tripJsonDAO = new TripJsonDAO();
    }
    @Test
    void createTrip() {
        Trip newTrip = new Trip();
        String tripName = "일본여행";
        String startDateStr = "2023-09-04";
        String endDateStr = "2023-09-10";
//        tripJsonDAO.createTrip(tripName, startDateStr, endDateStr);

    }

    @Test
    void insertItinerary() {
        int tripId = 4;
        String departurePlace = "도쿄";
        String destination = "오사카";
        String departureTimeString = "2023-07-04 12:30";
        String arrivalTimeString = "2023-08-04 15:30";
        String checkInString = "2023-09-04 16:00";
        String checkOutString = "2023-09-10 11:00";

        tripJsonDAO.insertItinerary(
                tripId,
                departurePlace,
                destination,
                departureTimeString,
                arrivalTimeString,
                checkInString,
                checkOutString
        );

        Trip updatedTrip = tripJsonDAO.selectTrip(tripId);
        System.out.println("updatedTrip = " + updatedTrip);    }



    @Test
    void selectTripList() {
        List<Trip> tripList = tripJsonDAO.selectTripList();
        System.out.println("tripList = " + tripList);
    }

    @Test
    void selectTrip() {
        Trip selectedTrip = tripJsonDAO.selectTrip(4);
        System.out.println("selectedTrip = " + selectedTrip);

    }

    @Test
    void countTripFiles() {

        int count = tripJsonDAO.countTripFiles();
        System.out.println("Number of trip files: " + count);
        assertEquals(3, count);

    }

    @Test
    void selectItinerary() {
        Itinerary selectedItinerary = tripJsonDAO.selectItinerary(4, 1);
    }
}
