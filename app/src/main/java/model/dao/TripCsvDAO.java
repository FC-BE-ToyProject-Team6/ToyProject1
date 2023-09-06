package model.dao;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TripCsvDAO implements TripDAO {
    private Trip trip;
    private Itinerary itinerary;
    private String directoryName = "./trip_csv_files/";

    @Override
    public int createTrip(Trip trip) {
        File dir = new File(directoryName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int tripId = countTripFiles() + 1;

        Date startDate = trip.getStartDate();
        Date endDate = trip.getEndDate();

        String fileName = tripId + ".csv";
        String fullPath = directoryName + "/"+ fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
            writer.write("trip_id,trip_name,start_date,end_date,itinerary_id,departure,destination,departure_ti\n" +
                    "me,arrival_time,accommodation,check_in,check_out");
            writer.newLine();
            writer.write(tripId + "," + trip.getTripName() + "," + startDate + "," + endDate);
            writer.newLine();
            System.out.println("File created at: " + new File(fullPath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tripId;
    }

    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {


        DateTime departureTime = itinerary.getDepartureTime();
        DateTime arrivalTime = itinerary.getArrivalTime();
        DateTime checkIn = itinerary.getCheckIn();
        DateTime checkOut = itinerary.getCheckOut();

        String fileName = tripId + ".csv";
        String fullPath = directoryName + "/" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, true))) {
            writer.write(tripId + "," + itinerary.getDeparturePlace() + "," + itinerary.getDestination() + "," +
                    departureTime + "," + arrivalTime + "," + checkIn + "," + checkOut);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<Trip> selectTripList() {
        List<Trip> tripList = new ArrayList<>();

        return tripList;
    }

    @Override
    public Trip selectTrip(int tripId) {
        String fileName = tripId + ".csv";
        String fullPath = directoryName + "/" + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            String line = reader.readLine();
            line = reader.readLine();

            if (line != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                Date startDate = Date.ofString(values[2]);
                Date endDate = Date.ofString(values[3]);
                return new Trip(id, name, startDate, endDate, new Itineraries());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countTripFiles() {
        File folder = new File(directoryName);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            return (int) listOfFiles.length;
        }
        return 0;
    }


    @Override
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        String fileName = tripId + ".csv";
        String fullPath = directoryName + "/" + fileName;

        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            String line;
            reader.readLine();  // Skip header

            int currentItineraryId = 1;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (currentItineraryId == itineraryId) {
                    int id = Integer.parseInt(values[0]);
                    String departurePlace = values[1];
                    String destination = values[2];
                    DateTime departureTime = DateTime.ofString(values[3]);
                    DateTime arrivalTime = DateTime.ofString(values[4]);
                    DateTime checkIn = DateTime.ofString(values[5]);
                    DateTime checkOut = DateTime.ofString(values[6]);
                    return new Itinerary(id, departurePlace, destination, departureTime, arrivalTime, checkIn, checkOut);
                }
                currentItineraryId++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
