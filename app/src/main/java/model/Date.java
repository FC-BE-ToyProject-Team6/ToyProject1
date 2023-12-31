package model;

import exception.IllegalDateException;
import java.time.YearMonth;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Date {

    private final int year;
    private final int month;
    private final int day;


    public Date(int year, int month, int day) {
        this.year = validateYear(year);
        this.month = validateMonth(month);
        this.day = validateDay(year, month, day);

    }

    /**
     * 문자열을 입력받아 Date를 반환 합니다.
     *
     * @param time 문자열 형식(2020-12-12)
     * @return Date
     */
    public static Date ofString(String time) {

        String[] times = time.split("-");
        int[] timesInt = parseTimeInt(times);

        return new Date(timesInt[0], timesInt[1], timesInt[2]);

    }

    static int[] parseTimeInt(String[] times) {

        int[] timesInt = new int[times.length];

        for (int i = 0; i < times.length; i++) {
            try {
                timesInt[i] = Integer.parseInt(times[i]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("날짜 입력 형식이 올바르지 않습니다.");
            }
        }

        return timesInt;
    }

    private int validateYear(int year) {
        if (!(1 <= year)) {
            throw new IllegalDateException("년도는 1보다 커야 합니다.");
        }
        return year;
    }

    private int validateMonth(int month) {
        if (!(1 <= month && month <= 12)) {
            throw new IllegalDateException("월은 1 ~ 12 사이 여야 합니다.");
        }
        return month;
    }

    private int validateDay(int year, int month, int day) {
        YearMonth yearMonth = YearMonth.of(year, month);
        if (!yearMonth.isValidDay(day)) {
            throw new IllegalDateException(
                "일은 1 ~ " + yearMonth.lengthOfMonth() + " 사이 여야 합니다.");
        }
        return day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }


}
