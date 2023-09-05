package controller;

import java.math.BigInteger;
import java.util.UUID;
import model.DateTime;
import model.Itinerary;

public class ItineraryInputController {

    public Itinerary createItinerary(String departurePlace, String destination,
        String departureTime, String arrivalTime, String checkIn, String checkOut) {

        int itineraryId = UUID.randomUUID().toString()
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


        return new Itinerary(
            itineraryId, departurePlace,destination,
            stringToDateTime(departureTime), stringToDateTime(arrivalTime),
            stringToDateTime(checkIn),stringToDateTime(checkOut)
        );

    }

    public DateTime stringToDateTime(String dateTimeString){
        String[] dateTimeArray = dateTimeString.split(" ");
        String[] dateArray = dateTimeArray[0].split("-");
        String[] timeArray = dateTimeArray[1].split(":");

        return new DateTime(
            Integer.parseInt(dateArray[0]), // 년도
            Integer.parseInt(dateArray[1]), // 월
            Integer.parseInt(dateArray[2]),  // 일
            Integer.parseInt(timeArray[0]), // 시
            Integer.parseInt(timeArray[1]) // 분
        );
    }
}
