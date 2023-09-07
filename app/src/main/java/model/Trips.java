package model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Trips {

    /**
     * -- GETTER --
     *  반복문등 List타입이 필요할때 List타입 반환합니다. Iterable 등 반복자를 구현할 필요성이 있음.
     *
     * @return List<Trip>
     */
    List<Trip> trips;

    public Trips() {
        trips = new ArrayList<>();
    }

    public Trips(List<Trip> trips) {
        this.trips = trips;
    }

    /**
     * Trips에 Trip을 추가 합니다.
     *
     * @param trip 여행정보
     * @return 추가에 성공하면 true 실패하면 false;
     */
    public boolean addTrip(Trip trip) {
        return this.trips.add(trip);
    }

    /**
     * ID를 이용해서 Tips에서 Trip을 가져옵니다. 값이 없으면 null을 반환합니다.
     *
     * @param tripId 여행의 Id
     * @return Trip, 값이 없으면 null
     */
    public Trip getTrips(int tripId) {
        Trip trip = null;
        for (Trip t : trips) {
            if (t.isIdEquals(tripId)) {
                trip = t;
            }
        }
        return trip;
    }

    public int size() {
        return trips.size();
    }

    public boolean isEmpty() {
        return trips.isEmpty();
    }
}
