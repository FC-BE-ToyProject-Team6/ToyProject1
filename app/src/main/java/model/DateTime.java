package model;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@EqualsAndHashCode(callSuper = true)
public class DateTime extends Date{

    private final int hour;
    private final int minute;
    public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.hour = validateHour(hour);
        this.minute = validateMinute(minute);
    }


    private int validateHour(int hour) {
        if(!(0 <= hour  && hour <= 23)){
            throw new IllegalArgumentException("시간은 0 ~ 23 사이 여야 합니다.");
        }
        return hour;
    }

    private int validateMinute(int minute) {
        if (!(0 <= minute && minute <= 59)) {
            throw new IllegalArgumentException("분은 0~59 사이 여야 합니다.");
        }
        return minute;
    }
    public static String dateTimeToString(DateTime dateTime) {

        return dateTime.toString();
    }

}
