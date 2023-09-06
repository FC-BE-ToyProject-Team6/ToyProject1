package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import model.Date;
import model.Itinerary;
import model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

class TripJsonDAOTest {
    private int lastTripId;

    private TripJsonDAO tripJsonDAO;
    private final String tripFilesFolder = "./trip_json_files/";

    @BeforeEach
    void setUp() {
        tripJsonDAO = new TripJsonDAO();
    }
    @Test
    void createTrip() {

            String tripName = "일본여행";
            String startDateStr = "2023-09-04";
            String endDateStr = "2023-09-10";

            Date startDate = Date.ofString(startDateStr);
            Date endDate = Date.ofString(endDateStr);

            Trip newTrip = new Trip(tripName, startDate, endDate);


            int tripId = tripJsonDAO.createTrip(newTrip);
        lastTripId = tripId;


        System.out.println("tripId = " + tripId);

            Trip savedTrip = tripJsonDAO.selectTrip(tripId);
            assertNotNull(savedTrip);
            assertEquals(tripName, savedTrip.getTripName());
            assertEquals(startDate, savedTrip.getStartDate());
            assertEquals(endDate, savedTrip.getEndDate());
        }




    @Test
    void insertItinerary() {


        String departurePlace = "도쿄";
        String destination = "오사카";
        String departureTimeString = "2023-07-04 12:30";
        String arrivalTimeString = "2023-08-04 15:30";
        String checkInString = "2023-09-04 16:00";
        String checkOutString = "2023-09-10 11:00";

        Itinerary itinerary = new Itinerary();
        int tripId = getLastTripId();
        tripJsonDAO.insertItinerary(tripId, itinerary);

        Trip updatedTrip = tripJsonDAO.selectTrip(tripId);
        Itinerary lastInsertedItinerary = updatedTrip.getItineraries().get(updatedTrip.getItineraries().size() - 1);  // Assuming the last one is the one you just inserted.

        assertEquals(departurePlace, lastInsertedItinerary.getDeparturePlace());
        assertEquals(destination, lastInsertedItinerary.getDestination());
    }

    private int getLastTripId() {
        return lastTripId;
    }

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
