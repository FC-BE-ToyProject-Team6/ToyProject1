package model.dao;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import common.FileStringUtil;
import lombok.Getter;
import model.DateTime;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
import model.TripDto;


public class TripJsonDAO implements TripDAO {

    @Getter
    private int lastTripId;
    private final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    @Override
    public int createTrip(Trip trip) {
        return 0;
    }

    public int createTrip(TripDto dto) {

        File dir = new File(FileStringUtil.DIR_PATH_JSON);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int tripId = countTripFiles() + 1;

        Trip trip = new Trip(
            tripId, dto.getTripName(), dto.getStartDate(), dto.getEndDate(), new Itineraries()
        );

        String fileName = String.format(FileStringUtil.FILE_PATH_JSON, tripId);

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(trip, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastTripId = tripId;
        return tripId;
    }


    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {

        String fileName = String.format(FileStringUtil.FILE_PATH_JSON, tripId);

        try (FileReader reader = new FileReader(fileName)) {
            Trip trip = gson.fromJson(reader, Trip.class);
            if (trip.getItineraries() == null) {
                trip.setItineraries(new Itineraries());
            }
            int itineraryId = trip.getItineraries().size() + 1;
            DateTime departureTime = itinerary.getDepartureTime();
            DateTime arrivalTime = itinerary.getArrivalTime();
            DateTime checkIn = itinerary.getCheckIn();
            DateTime checkOut = itinerary.getCheckOut();

            Itinerary newItinerary = new Itinerary();
            newItinerary.setItineraryId(itineraryId);
            newItinerary.setDeparturePlace(itinerary.getDeparturePlace());
            newItinerary.setDestination(itinerary.getDestination());
            newItinerary.setDepartureTime(departureTime);
            newItinerary.setArrivalTime(arrivalTime);
            newItinerary.setCheckIn(checkIn);
            newItinerary.setCheckOut(checkOut);

            trip.getItineraries().add(newItinerary);
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(trip, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Trip> selectTripList() {
        List<Trip> tripList = new ArrayList<>();
        File folder = new File(FileStringUtil.DIR_PATH_JSON);
        File[] listOfFiles = folder.listFiles();

        Type tripType = new TypeToken<Trip>() {
        }.getType();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(FileStringUtil.FILE_FORMAT_JSON)) {
                    try (FileReader reader = new FileReader(file)) {
                        Trip trip = gson.fromJson(reader, tripType);
                        tripList.add(trip);
                    } catch (JsonSyntaxException ex) {
                        /* Json 형태 유효성 검사 */
                        System.out.println("잘못된 JSON 형식입니다.");
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return tripList;
    }


    @Override
    public Trip selectTrip(int tripId) {
        String fileName = String.format(FileStringUtil.FILE_PATH_JSON, tripId);

        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Trip.class);
        } catch (JsonSyntaxException ex) {
            /* Json 형태 유효성 검사 */
            System.out.println("잘못된 JSON 형식입니다.");
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public int countTripFiles() {
        File folder = new File(FileStringUtil.DIR_PATH_JSON);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            return (int) Arrays.stream(listOfFiles)
                .filter(file -> file.isFile() && file.getName().endsWith(FileStringUtil.FILE_FORMAT_JSON))
                .count();
        }
        return 0;
    }

    @Override
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        String fileName = String.format(FileStringUtil.FILE_PATH_JSON, tripId);
        File file = new File(fileName);

        try (FileReader reader = new FileReader(file)) {
            Trip trip = gson.fromJson(reader, Trip.class);

            Itinerary selectedItinerary = trip.getItineraries().get(itineraryId - 1);
            return selectedItinerary;

        } catch (JsonSyntaxException ex) {
            /* Json 형태 유효성 검사 */
            System.out.println("잘못된 JSON 형식입니다.");
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}


