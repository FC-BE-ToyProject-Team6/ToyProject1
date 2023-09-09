package common;

import exception.IllegalDateException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Date;
import model.DateTime;

public class Scan {

    private static final String defaultAlert = "올바른 입력이 아닙니다.";
    private static final String intRegex = "[0-9]+";
    private static final String AllWordsRegex = "[0-9a-zA-Zㄱ-ㅎ가-힣 ]*";
    private static final String dateRegex = "\\d{4}-(0[1-9]|1[012])-(0[0-9]|[12][0-9]|3[01])";
    private static final String dateTimeRegex =
        dateRegex + " (0[1-9]|1[0-9]|2[0-4]):(0[0-9]|[1-5][0-9])";
    private static final String ynRegex = "[y|Y|n|N]";

    private static final BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));


    public static String nextLine(String msg, String regex, String alert) {
        while (true) {
            try {
                StringUtil.inputValue(msg);
                String s = sc.readLine();
                if (!s.matches(regex)) {
                    throw new IllegalArgumentException(alert);
                }
                return s;
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String nextLine(String msg) {
        return nextLine(msg, AllWordsRegex, defaultAlert);
    }

    public static String nextLine(String msg, String alert) {
        return nextLine(msg, AllWordsRegex, alert);
    }

    public static int nextInt(String msg) {
        return Integer.parseInt(nextLine(msg, intRegex, defaultAlert));
    }

    public static int nextInt(String msg, String alert) {
        return Integer.parseInt(nextLine(msg, intRegex, alert));
    }

    public static int nextInt(String msg, int start, int end) {
        String regex = "[" + start + "-" + end + "]";
        return Integer.parseInt(nextLine(msg, regex, defaultAlert));
    }

    public static int nextInt(String msg, int start, int end, String alert) {
        String regex = "[" + start + "-" + end + "]";
        return Integer.parseInt(nextLine(msg, regex, alert));
    }

    public static Date nextDate(String msg) {
        while(true){
            try {
                return Date.ofString(nextLine(msg, dateRegex, defaultAlert));
            }catch (IllegalDateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static Date nextDate(String msg, String alert) {
        while(true){
            try {
                return Date.ofString(nextLine(msg, dateRegex, alert));
            }catch (IllegalDateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static DateTime nextDateTime(String msg) {
        while(true){
            try {
                return DateTime.ofString(nextLine(msg, dateTimeRegex, defaultAlert));
            }catch (IllegalDateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static DateTime nextDateTime(String msg, String alert) {
        while(true){
            try {
                return DateTime.ofString(nextLine   (msg, dateTimeRegex, alert));
            }catch (IllegalDateException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String nextYN(String msg) {
        return nextLine(msg, ynRegex, defaultAlert).toUpperCase();
    }

}
