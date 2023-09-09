package controller;

import model.dao.TripDAO;
import model.dao.TripJsonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItinerarySelectControllerTest {
    TripDAO tripDAO;
    ItinerarySelectController itinerarySelectController;
    @BeforeEach
    void setUp() {
        tripDAO = new TripJsonDAO();
        itinerarySelectController = new ItinerarySelectController();
    }

    @Test
    public void print() {
        System.out.println(tripDAO.countTripFiles());
        //System.out.println(itinerarySelectController.getTrips());
        System.out.println(itinerarySelectController.getItineraryBySearchId(1,1));
        System.out.println(itinerarySelectController.getTripBySearchId(2));
        System.out.println(itinerarySelectController.getTripBySearchId(4));

    }
    @Test
    void isTripsEmpty() {
    }
}