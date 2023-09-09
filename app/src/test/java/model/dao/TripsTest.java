package model.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.Date;
import model.itinerary.Itineraries;
import model.trip.Trip;
import model.trip.Trips;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TripsTest {

    Trips trips;

    @BeforeEach
    public void setUp() {
        Trip trip1 = new Trip(1, "여행1"
            , Date.ofString("2020-12-12"), Date.ofString("2020-12-12")
            , new Itineraries()
        );
        Trip trip2 = new Trip(2, "여행2"
            , Date.ofString("2020-12-12"), Date.ofString("2020-12-12")
            , new Itineraries()
        );
        List<Trip> list = new ArrayList<>();
        list.add(trip1);
        list.add(trip2);
        trips = new Trips(list);
    }

    @Test
    public void sizeTrips() {
        assertThat(trips.size()).isEqualTo(2);
    }

    @Test
    public void addTrips() {
        Trip trip = new Trip(3, "여행3"
            , Date.ofString("2020-12-12"), Date.ofString("2020-12-12")
            , new Itineraries()
        );
        trips.addTrip(trip);
        assertThat(trips.size()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void getId(int tripId) {
        Trip trip = trips.getTrips(tripId);
        assertThat(trip.getTripId()).isEqualTo(tripId);
    }
}
