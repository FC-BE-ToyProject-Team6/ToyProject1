package model.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class TripJsonDAO implements TripDAO {
    private int lastTripId;
    private Gson gson = new Gson();
    private String directoryName = "./trip_json_files/";

    @Override
    public int createTrip(Trip trip) {

        File dir = new File(directoryName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int tripId = countTripFiles() + 1;

        trip.setTripId(tripId);
        trip.setItineraries(new Itineraries());

        String fileName = "travel_"+ tripId+ ".json";
        String fullPath = directoryName + "/" + fileName;

        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(trip, writer);
            System.out.println("File created at: " + new File(fullPath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastTripId = tripId;
        return tripId;
    }

    public int getLastTripId() {
        return lastTripId;
    }



    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {

        String fileName = "travel_"+ tripId+ ".json";
        String fullPath = directoryName+"/"+ fileName;
        System.out.println("Attempting to read from path: " + fullPath);

        try (FileReader reader = new FileReader(fullPath)) {
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
            try (FileWriter writer = new FileWriter(fullPath)) {
                gson.toJson(trip, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Trip> selectTripList() {
        List<Trip> tripList = new ArrayList<>();
        File folder = new File(directoryName);
        File[] listOfFiles = folder.listFiles();

        Type tripType = new TypeToken<Trip>() {
        }.getType();

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
        for (Trip trip : tripList) {
            System.out.println(gson.toJson(trip));
        }

        return tripList;
    }


    @Override
    public Trip selectTrip(int tripId) {
        try (FileReader reader = new FileReader(directoryName+"travel_"+tripId + ".json")) {
            return gson.fromJson(reader, Trip.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public int countTripFiles() {
        File folder = new File(directoryName);
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

        File file = new File(directoryName+"travel_"+tripId + ".json");

        try (FileReader reader = new FileReader(file)) {
            Trip trip = gson.fromJson(reader, Trip.class);

            Itinerary selectedItinerary = trip.getItineraries().get(itineraryId - 1);
            System.out.println(gson.toJson(selectedItinerary));
            return selectedItinerary;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


