package model.dao;


import static org.junit.jupiter.api.Assertions.*;

import model.Itinerary;
import model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class TripCsvDAOTest {
    private TripCsvDAO tripCsvDAO;
    private final String tripFilesFolder = "./trip_csv_files/";

    @BeforeEach
    void setUp() {
        tripCsvDAO = new TripCsvDAO();
    }

    @Test
    void createTrip() {
        String tripName = "일본여행";
        String startDateStr = "2023-09-04";
        String endDateStr = "2023-09-10";
        tripCsvDAO.createTrip(tripName, startDateStr, endDateStr);

    }

    @Test
    void insertItinerary() {
        int tripId = 1;
        String departurePlace = "Tokyo";
        String destination = "Osaka";
        String departureTimeString = "2023-07-04 12:30";
        String arrivalTimeString = "2023-08-04 15:30";
        String checkInString = "2023-09-04 16:00";
        String checkOutString = "2023-09-10 11:00";

        tripCsvDAO.insertItinerary(
                tripId,
                departurePlace,
                destination,
                departureTimeString,
                arrivalTimeString,
                checkInString,
                checkOutString
        );

        Trip updatedTrip = tripCsvDAO.selectTrip(tripId);
    }

    @Test
    void selectTripList() {
        List<Trip> tripList = tripCsvDAO.selectTripList();
    }

    @Test
    void selectTrip() {
        Trip selectedTrip = tripCsvDAO.selectTrip(1);
    }

    @Test
    void countTripFiles() {
        int count = tripCsvDAO.countTripFiles();
        assertEquals(1, count);
    }

    @Test
    void selectItinerary() {
        Itinerary selectedItinerary = tripCsvDAO.selectItinerary(1, 1);
    }
}
