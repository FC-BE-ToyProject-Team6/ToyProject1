package model.dao;

import model.itinerary.Itinerary;
import model.itinerary.ItineraryDto;
import model.trip.Trip;
import model.trip.TripDto;
import model.trip.Trips;

public interface TripDAO {


    int createTrip(TripDto trip);

    void insertItinerary(int tripId, ItineraryDto itinerary);

    Trips selectTripList();

    Trip selectTrip(int tripId);

    int countTripFiles();

    Itinerary selectItinerary(int tripId, int itineraryId);

    /**
     * createTrip, 처음에 여행 파일 없을 때 생성
     * insertItinerary, 기존에 있는 여행 파일에 여정 추가할 때
     * selectTripList, TripList 불러오기
     * selectTrip, 특정 Trip 정보 불러오기
     * countTripFiles, Trip 파일 개수 세기
     * selectItinerary, 특정 trip 파일에서 특정 여정 정보 출력하기
     */
}


