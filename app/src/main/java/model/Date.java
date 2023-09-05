package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

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

    public static Date fromString(String tripDatum) {
        try {
            LocalDate localDate = LocalDate.parse(tripDatum);
            return new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        } catch (DateTimeParseException e) {
            // Handle exception
            System.err.println("Invalid date format: " + tripDatum);
            return null; // or throw new RuntimeException("Invalid date format");
        }
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
