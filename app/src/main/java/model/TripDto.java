package model;

import lombok.Getter;

@Getter
public class TripDto {

    private final String tripName;
    private final Date startDate;
    private final Date endDate;

    public TripDto(String tripName, String startDate, String endDate) {
        this.tripName = tripName;
        this.startDate = Date.ofString(startDate);
        this.endDate = Date.ofString(endDate);
    }
}
