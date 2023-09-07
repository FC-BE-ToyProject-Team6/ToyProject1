package model.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Date;
import model.DateTime;
import model.Itineraries;
import model.Itinerary;
import model.Trip;
public class TripCsvDAO implements TripDAO {
    private Trip trip;
    private Itinerary itinerary;
    private String directoryName = "app/trip_csv_files/";

    @Override
    public int createTrip(Trip trip) {
        File dir = new File(directoryName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int tripId = countTripFiles() + 1;
        Date startDate = trip.getStartDate();
        Date endDate = trip.getEndDate();
        String fileName = "travel_"+tripId + ".csv";
        String fullPath = directoryName + "/"+ fileName;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
            writer.write("trip_id,trip_name,start_date,end_date,itinerary_id,departure,"
                + "destination,departure_time,arrival_time,check_in,check_out");
            writer.newLine();
            writer.write(tripId + "," + trip.getTripName() + "," + startDate + "," + endDate+
                ","+","+","+","+","+","+",");
            writer.newLine();
            System.out.println("File created at: " + new File(fullPath).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tripId;
    }
    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {
        try {
            String fileName = directoryName + "travel_" + tripId + ".csv";
            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> csvData = csvReader.readAll();
            int itinerayId = csvData.size();

            //파일 당 여행 1개라 여행 id가 같은지 비교할 필요 없음
            for (String[] row : csvData) {
                    row[4] = String.valueOf(itinerayId++);
                    row[5] = itinerary.getDeparturePlace();
                    row[6] = itinerary.getDestination();
                    row[7] = itinerary.getDepartureTime().toString();
                    row[8] = itinerary.getArrivalTime().toString();
                    row[9] = itinerary.getCheckIn().toString();
                    row[10] = itinerary.getCheckOut().toString();
            }

            FileWriter fileWriter = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeAll(csvData);

            csvReader.close();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
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
    public List<Trip> selectTripList() {
        List<Trip> tripList = new ArrayList<>();
        return tripList;
    }

    @Override
    public Trip selectTrip(int tripId) {
        String fileName = "travel_" + tripId + ".csv";
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
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        String fileName = "travel_"+tripId + ".csv";
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