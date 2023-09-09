package model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.Date;
import model.Itinerary;
import model.Trip;
import model.Trips;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TripCsvDAOTest {

    private final String tripFilesFolder = "app/trip_csv_files/";
    private TripCsvDAO tripCsvDAO;

    @BeforeEach
    void setUp() {
        tripCsvDAO = new TripCsvDAO();
    }

    @Test
    void createTrip() {
        String tripName = "일본여행";
        String startDateStr = "2023-09-04";
        String endDateStr = "2023-09-10";
        Trip trip = new Trip();
        trip.setTripName(tripName);
        trip.setStartDate(Date.ofString(startDateStr));
        trip.setEndDate(Date.ofString(endDateStr));
        int createdTripId = tripCsvDAO.createTrip(trip);
        System.out.println("createdTripId = " + createdTripId);
        String expectedFilePath = tripFilesFolder + "/travel_" + createdTripId + ".csv";
    }

    @Test
    void insertItinerary() {
        int tripId = 12;
        String departurePlace = "Tokyo";
        String destination = "Osaka";
        String departureTimeString = "2023-07-04 12:30";
        String arrivalTimeString = "2023-08-04 15:30";
        String checkInString = "2023-09-04 16:00";
        String checkOutString = "2023-09-10 11:00";

        Itinerary itinerary = new Itinerary(
            departurePlace, destination,
            departureTimeString, arrivalTimeString,
            checkInString, checkOutString
        );

        tripCsvDAO.insertItinerary(
            tripId,
            itinerary
        );
        Trip updatedTrip = tripCsvDAO.selectTrip(tripId);
    }

    @Test
    void selectTripList() {
        Trips tripList = tripCsvDAO.selectTripList();
        System.out.println("tripList = " + tripList);
    }

    @Test
    void selectTrip() {
        Trip selectedTrip = tripCsvDAO.selectTrip(6);
    }

    @Test
    void countTripFiles() {
        int count = tripCsvDAO.countTripFiles();
        assertEquals(7, count);
    }

    @Test
    void selectItinerary() {
        Itinerary selectedItinerary = tripCsvDAO.selectItinerary(7, 1);
    }
}