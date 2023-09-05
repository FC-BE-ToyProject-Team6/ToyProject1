package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class DateTime extends Date {

    private final int hour;
    private final int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.hour = validateHour(hour);
        this.minute = validateMinute(minute);
    }

    private DateTime(Date date, int hour, int minute) {
        this(date.getYear(), date.getMonth(), date.getDay(), hour, minute);
    }


    /**
     * 문자열을 입력받아 DateTime을 반환 합니다.
     *
     * @param time 문자열 형식(2020-12-12 13:31)
     * @return DateTime
     */
    public static DateTime ofString(String time) {

        String[] dates = time.split(" ");

        Date date = Date.ofString(dates[0]);

        String[] times = dates[1].split(":");
        int[] timesInt = Date.parseTimeInt(times);

        return new DateTime(date, timesInt[0], timesInt[1]);

    }

    private int validateHour(int hour) {
        if (!(0 <= hour && hour <= 23)) {
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

}
