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

class TripJsonDAOTest {

    private final String tripFilesFolder = "app/trip_json_files/";
    private int lastTripId;
    private TripJsonDAO tripJsonDAO;

    @BeforeEach
    void setUp() {
        tripJsonDAO = new TripJsonDAO();
    }

    @Test
    void createTrip() {

        String tripName = "유럽여행";
        Date startDate = Date.ofString("2023-08-04");
        Date endDate = Date.ofString("2023-08-10");

        TripDto newTrip = new TripDto(tripName, startDate, endDate);

        int tripId = tripJsonDAO.createTrip(newTrip);
        lastTripId = tripId;

        Trip savedTrip = tripJsonDAO.selectTrip(tripId);
        System.out.println("savedTrip = " + savedTrip);
    }


    @Test
    void insertItinerary() {
        int tripId = 2;

        String departurePlace = "필라델피아";
        String destination = "뉴욕";
        String departureTimeString = "2023-09-20 12:30";
        String arrivalTimeString = "2023-09-20 15:30";
        String checkInString = "2023-09-20 16:00";
        String checkOutString = "2023-09-30 11:00";

        ItineraryDto itinerary = new ItineraryDto(
            departurePlace,
            destination,
            DateTime.ofString(departureTimeString),
            DateTime.ofString(arrivalTimeString),
            DateTime.ofString(checkInString),
            DateTime.ofString(checkOutString)
        );

        tripJsonDAO.insertItinerary(tripId, itinerary);

        Trip updatedTrip = tripJsonDAO.selectTrip(tripId);
        Itinerary lastInsertedItinerary = updatedTrip.getItineraries()
            .get(updatedTrip.getItineraries().size() - 1);

        assertEquals(departurePlace, lastInsertedItinerary.getDeparturePlace());
        assertEquals(destination, lastInsertedItinerary.getDestination());
    }

    private int getLastTripId() {
        return lastTripId;
    }

    @Test
    void selectTripList() {

        Trips tripList = tripJsonDAO.selectTripList();
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
