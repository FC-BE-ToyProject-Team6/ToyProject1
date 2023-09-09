package model.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import common.FileStringUtil;
import model.*;

public class TripCsvDAO implements TripDAO {

    private Trip trip;
    private Itinerary itinerary;

    @Override
    public int createTrip(Trip trip) {
        return 0;
    }

    public int createTrip(TripDto trip) {
        int tripId = countTripFiles() + 1;

        try {
            File dir = new File(FileStringUtil.DIR_PATH_CSV);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
            FileOutputStream fileStream = new FileOutputStream(fileName);
            OutputStreamWriter fileWriter = new OutputStreamWriter(fileStream,
                StandardCharsets.UTF_8);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            List<String[]> rowList = new ArrayList<>();
            rowList.add(new String[]{
                "trip_id", "trip_name", "start_date", "end_date",
                "itinerary_id", "departure", "destination", "departure_time", "arrival_time",
                "check_in", "check_out"
            });
            rowList.add(new String[]{
                String.valueOf(tripId), trip.getTripName(),
                trip.getStartDate().toString(), trip.getEndDate().toString(),
                "", "", "", "", "", "", ""
            });
            csvWriter.writeAll(rowList, true);

            csvWriter.close();
            fileWriter.close();
            fileStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tripId;
    }


    @Override
    public void insertItinerary(int tripId, Itinerary itinerary) {
        try {
            String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> csvData = csvReader.readAll();

            int itinerayId = csvData.size();
            String[] newRow = new String[11];

            newRow[4] = String.valueOf(itinerayId++);
            newRow[5] = itinerary.getDeparturePlace();
            newRow[6] = itinerary.getDestination();
            newRow[7] = itinerary.getDepartureTime().toString();
            newRow[8] = itinerary.getArrivalTime().toString();
            newRow[9] = itinerary.getCheckIn().toString();
            newRow[10] = itinerary.getCheckOut().toString();

            csvData.add(newRow);

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
        File folder = new File(FileStringUtil.DIR_PATH_CSV);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            return listOfFiles.length;
        }
        return 0;
    }

    @Override
    public Trips selectTripList() {
        List<Trip> tripList = new ArrayList<>();

        return new Trips(tripList);
    }

    @Override
    public Trip selectTrip(int tripId) {
        String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

        String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
                    return new Itinerary(id, departurePlace, destination, departureTime,
                        arrivalTime, checkIn, checkOut);
                }
                currentItineraryId++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}