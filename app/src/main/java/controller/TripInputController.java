package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import model.Date;
import model.DateTime;
import model.Itineraries;
import model.Itinerary;
import model.Trip;

public class TripInputController {

    public Trip createTrip(String tripName, String startDate, String endDate) {
        int tripId = UUID.randomUUID().toString()
            .replace("-", "").chars()
            .mapToObj(c -> Character.toString((char) c))
            .reduce
                (BigInteger.ZERO,
                    (acc, s) ->
                        acc.multiply(BigInteger.valueOf(16)).add(new BigInteger(s, 16)),
                    BigInteger::add
                )
            .mod(BigInteger.valueOf(Integer.MAX_VALUE))
            .intValue();

        List<Itinerary> itineraries = new ArrayList<>();

        return new Trip(
            tripId,
            tripName,
            stringToDate(startDate),
            stringToDate(endDate),
            (Itineraries) itineraries
        );

    }

    public Date stringToDate(String dateString) {
        String[] dateArray = dateString.split("-");

        return new Date(
            Integer.parseInt(dateArray[0]), // 년도
            Integer.parseInt(dateArray[1]), // 월
            Integer.parseInt(dateArray[2])  // 일
        );
    }


}
