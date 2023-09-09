package model.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Date;
import model.DateTime;
import model.itinerary.Itinerary;
import model.itinerary.ItineraryDto;
import model.trip.Trip;
import model.trip.TripDto;
import model.trip.Trips;
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
        TripDto trip = new TripDto(tripName, Date.ofString(startDateStr), Date.ofString(endDateStr));
        int createdTripId = tripCsvDAO.createTrip(trip);
        System.out.println("createdTripId = " + createdTripId);
        String expectedFilePath = tripFilesFolder + "/travel_" + createdTripId + ".csv";
    }

    @Test
    void insertItinerary() {
        int tripId = 12;
        String departurePlace = "Tokyo";
        String destination = "Osaka";
        DateTime departureTimeString = DateTime.ofString("2023-07-04 12:30");
        DateTime arrivalTimeString = DateTime.ofString("2023-08-04 15:30");
        DateTime checkInString = DateTime.ofString("2023-09-04 16:00");
        DateTime checkOutString = DateTime.ofString("2023-09-10 11:00");

        ItineraryDto itinerary = new ItineraryDto(
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