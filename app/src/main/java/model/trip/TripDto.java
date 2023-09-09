package model.trip;

import lombok.Getter;
import model.Date;

@Getter
public class TripDto {

    private final String tripName;
    private final Date startDate;
    private final Date endDate;

    public TripDto(String tripName, Date startDate, Date endDate) {
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
