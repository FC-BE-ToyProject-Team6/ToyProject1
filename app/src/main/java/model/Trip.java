package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Trip {
    private final int tripId;
    private final String tripName;
    private final Date startDate;
    private final Date endDate;
    private final Itineraries itineraries;
}