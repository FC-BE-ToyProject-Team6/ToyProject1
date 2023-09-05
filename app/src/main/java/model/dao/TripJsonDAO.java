package model.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Trip;
import model.Itinerary;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripJsonDAO implements TripDAO {

    private Gson gson = new Gson();

    @Override
    public void createTrip(Trip trip) {
        try (FileWriter writer = new FileWriter(trip.getTripId() + ".json")) {
            gson.toJson(trip, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {
        try (FileReader reader = new FileReader(tripId + ".json")) {
            Trip trip = gson.fromJson(reader, Trip.class);
            trip.getItineraries().add(itinerary);
            try (FileWriter writer = new FileWriter(tripId + ".json")) {
                gson.toJson(trip, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> selectTripList() {
        List<Trip> tripList = new ArrayList<>();
        File folder = new File("trip_files/");
        File[] listOfFiles = folder.listFiles();

        Type tripType = new TypeToken<Trip>(){}.getType();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try (FileReader reader = new FileReader(file)) {
                        Trip trip = gson.fromJson(reader, tripType);
                        tripList.add(trip);
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
        try (FileReader reader = new FileReader(tripId + ".json")) {
            return gson.fromJson(reader, Trip.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int countTripFiles() {
        File folder = new File("trip_files/");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            return (int) Arrays.stream(listOfFiles)
                    .filter(file -> file.isFile() && file.getName().endsWith(".json"))
                    .count();
        }
        return 0;
    }

    @Override
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        try (FileReader reader = new FileReader(tripId + ".json")) {
            Trip trip = gson.fromJson(reader, Trip.class);
            if (trip != null && trip.getItineraries() != null) {
                return trip.getItineraries().get(itineraryId);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

}}
