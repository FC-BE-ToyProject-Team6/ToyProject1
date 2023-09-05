package model.dao;

import model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TripCsvDAO implements TripDAO {

    @Override
    public void createTrip(Trip trip) {
        try (FileWriter writer = new FileWriter(trip.getTripId() + ".csv")) {
            writer.append("TripID,TripName,StartDate,EndDate\n");
            writer.append(trip.getTripId() + "," + trip.getTripName() + "," + trip.getStartDate() + "," + trip.getEndDate() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {
        // Append new itinerary to existing CSV file
        try (FileWriter writer = new FileWriter(tripId + ".csv", true)) {
            writer.append(itinerary.getItineraryId() + "," + itinerary.getDeparturePlace() + ","
                    + itinerary.getDestination() + "," + itinerary.getDepartureTime() + ","
                    + itinerary.getArrivalTime() + "," + itinerary.getCheckIn() + "," + itinerary.getCheckOut() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> selectTripList() {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        List<Trip> tripList = new ArrayList<>();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    try {
                        List<String> lines = Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
                        String[] tripData = lines.get(0).split(",");
                        Date startDate = Date.fromString(tripData[2]); // 가정: Date 클래스에 fromString 메서드가 있다.
                        Date endDate = Date.fromString(tripData[3]);
                        tripList.add(new Trip(Integer.parseInt(tripData[0]), tripData[1], startDate, endDate, null));
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
        try {
            List<String> lines = Files.lines(Paths.get(tripId + ".csv")).collect(Collectors.toList());
            String[] tripData = lines.get(0).split(",");

            // DateTime과 Date로 변환
            Date startDate = Date.fromString(tripData[2]);
            Date endDate = Date.fromString(tripData[3]);

            return new Trip(Integer.parseInt(tripData[0]), tripData[1], startDate, endDate, null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int countTripFiles() {

        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        int count = 0;

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    count++;
                }
            }
        }
        return count;
    }


    @Override
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        try {
            List<String> lines = Files.lines(Paths.get(tripId + ".csv")).collect(Collectors.toList());
            for (int i = 1; i < lines.size(); i++) {
                String[] itineraryData = lines.get(i).split(",");
                if (Integer.parseInt(itineraryData[0]) == itineraryId) {

                    // DateTime으로 변환
                    DateTime departureTime = (DateTime) DateTime.fromString(itineraryData[3]);
                    DateTime arrivalTime = (DateTime) DateTime.fromString(itineraryData[4]);
                    DateTime checkIn = (DateTime) DateTime.fromString(itineraryData[5]);
                    DateTime checkOut = (DateTime) DateTime.fromString(itineraryData[6]);

                    return new Itinerary(Integer.parseInt(itineraryData[0]), itineraryData[1], itineraryData[2],
                            departureTime, arrivalTime, checkIn, checkOut);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}