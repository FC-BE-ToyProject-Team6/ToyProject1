package model.dao;

import static model.DateTime.ofString;
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
    private final String tripFilesFolder = "app/trip_json_files/";


    @BeforeEach
    void setUp() {
        tripJsonDAO = new TripJsonDAO();
    }
    @Test
    void createTrip() {

        String tripName = "영국여행";
        String startDate = "2023-08-04";
        String endDate = "2023-08-10";

        Trip newTrip = new Trip(tripName, startDate, endDate);


        int tripId = tripJsonDAO.createTrip(newTrip);
        lastTripId = tripId;

        Trip savedTrip = tripJsonDAO.selectTrip(tripId);
        System.out.println("savedTrip = " + savedTrip);
    }




    @Test
    void insertItinerary() {
        int tripId = 2;

        String departurePlace = "뉴욕";
        String destination = "워싱턴";
        String departureTimeString = "2023-09-20 12:30";
        String arrivalTimeString = "2023-09-20 15:30";
        String checkInString = "2023-09-20 16:00";
        String checkOutString = "2023-09-30 11:00";

        Itinerary itinerary = new Itinerary();
        itinerary.setDeparturePlace(departurePlace);
        itinerary.setDestination(destination);
        itinerary.setDepartureTime(ofString(departureTimeString));  // Assuming DateTime.parse() is a method to parse your date-time string to your DateTime object
        itinerary.setArrivalTime(ofString(arrivalTimeString));
        itinerary.setCheckIn(ofString(checkInString));
        itinerary.setCheckOut(ofString(checkOutString));

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
        assertEquals(20, count);

    }

    @Test
    void selectItinerary() {
        Itinerary selectedItinerary = tripJsonDAO.selectItinerary(20, 2);
    }
}
