package model.dao;

import com.google.gson.JsonSyntaxException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import common.FileStringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Date;
import model.DateTime;
import model.itinerary.Itineraries;
import model.itinerary.Itinerary;
import model.itinerary.ItineraryDto;
import model.trip.Trip;
import model.trip.TripDto;
import model.trip.Trips;

public class TripCsvDAO implements TripDAO {

    private Trip trip;
    private Itinerary itinerary;

    @Override
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
    public void insertItinerary(int tripId, ItineraryDto itinerary) {
        try {

            String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
            FileReader fileReader = new FileReader(fileName);
            CSVReader csvReader = new CSVReader(fileReader);
            List<String[]> csvData = csvReader.readAll();

            int itinerayId = csvData.size() - 1;
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
        File folder = new File(FileStringUtil.DIR_PATH_CSV);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                String fileName = FileStringUtil.DIR_PATH_CSV + file.getName();
                Trip trip = selectTrip(fileName);
                tripList.add(trip);
            }
        }
        return new Trips(tripList);
    }

    public Trip selectTrip(String fileName) {
        Trip trip = null;
        Itineraries itineraries = new Itineraries();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine(); //헤더라 무시
            line = reader.readLine();
            int id = 0;
            String name = "";
            Date startDate = null, endDate = null;
            if (line != null) {
                String[] values = line.replaceAll("\"","").split(",");
                id = Integer.parseInt(values[0]);
                name = values[1];
                startDate = Date.ofString(values[2]);
                endDate = Date.ofString(values[3]);
            }

            /* 여정 조회 라인 */
            while ((line = reader.readLine()) != null) {
                String[] values = line.replaceAll("\"","").split(",");
                int j = 0;
                while (values[j++].equals(""));
                j--;
                int Itid = Integer.parseInt(values[j+0]);
                String departurePlace = values[j+1];
                String destination = values[j+2];
                DateTime departureTime = DateTime.ofString(values[j+3]);
                DateTime arrivalTime = DateTime.ofString(values[j+4]);
                DateTime checkIn = DateTime.ofString(values[j+5]);
                DateTime checkOut = DateTime.ofString(values[j+6]);
                itineraries.add(new Itinerary(Itid, departurePlace, destination, departureTime, arrivalTime, checkIn, checkOut));
            }
            trip = new Trip(id, name, startDate, endDate, itineraries);

        } catch (Exception e) {
            return null;
        }
        return trip;
    }

    @Override
    public Trip selectTrip(int tripId) {
        String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
        return selectTrip(fileName);
    }

    @Override
    public Itinerary selectItinerary(int tripId, int itineraryId) {
        String fileName = String.format(FileStringUtil.FILE_PATH_CSV, tripId);
        Trip trip = selectTrip(fileName);
        Itineraries itineraries = trip.getItineraries();
        Itinerary itinerary;
        try {
            itinerary = itineraries.get(itineraryId - 1);
        } catch (Exception e) {
            return null;
        }
        return itinerary;
    }

}