package model;

import java.time.YearMonth;
import lombok.EqualsAndHashCode;

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

    private int validateYear(int year) {
        if(!(1 <= year)){
            throw new IllegalArgumentException("년도는 1보다 커야 합니다.");
        }
        return year;
    }
    private int validateMonth(int month) {
        if(!(1 <= month && month <= 12)){
            throw new IllegalArgumentException("월은 1 ~ 12 사이 여야 합니다.");
        }
        return  month;
    }

    private int validateDay(int year, int month, int day) {
        YearMonth yearMonth = YearMonth.of(year, month);
        if(!yearMonth.isValidDay(day)){
            throw new IllegalArgumentException("일은 1 ~ " + yearMonth.lengthOfMonth() + " 사이 여야 합니다.");
        }
        return day;
    }









}
