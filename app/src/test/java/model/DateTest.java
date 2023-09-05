package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class DateTest {

    private int year = 2023;
    private int month = 12;
    private int day = 31;

    @Test
    public void Date클래스는년월일비교가같나요() {
        Date date = new Date(year, month, day);
        assertThat(date).isEqualTo(new Date(year, month, day));
    }

    @Test
    public void Date문자열인스턴스확인() {

        Date date = Date.ofString("2023-12-31");
        assertThat(date).isEqualTo(new Date(year, month, day));

    }

    @Test
    public void Date문자열잘못입력시에러발생() {

        assertThatThrownBy(() -> {
            Date date = Date.ofString("2023%12-31");
        }).isInstanceOf(NumberFormatException.class)
            .hasMessageContaining("날짜 입력 형식이 올바르지 않습니다.");


    }

    @Test
    public void 잘못된년도입력() {

        assertThatThrownBy(() -> {
            Date date = new Date(-1, month, day);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("년도는 1보다 커야 합니다.");

    }

    @Test
    public void 잘못된0월입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(year, 0, day);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("월은 1 ~ 12 사이 여야 합니다.");
    }

    @Test
    public void 잘못된13월입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(year, 13, day);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("월은 1 ~ 12 사이 여야 합니다.");
    }

    @Test
    public void 잘못된0일입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(year, month, 0);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("일은 1 ~ 31 사이 여야 합니다.");
    }

    @Test
    public void 잘못된32일입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(year, month, 32);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("일은 1 ~ 31 사이 여야 합니다.");
    }

    @Test
    public void 윤년아닌데29일입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(year, 2, 29);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("일은 1 ~ 28 사이 여야 합니다.");
    }

    @Test
    public void 윤년에정상적인29일입력() {

        Date date = new Date(2024, 2, 29);
        assertThat(date).isEqualTo(new Date(2024, 2, 29));
    }

    @Test
    public void 윤년에비정상적30일입력() {
        assertThatThrownBy(() -> {
            Date date = new Date(2024, 2, 30);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("일은 1 ~ 29 사이 여야 합니다.");
    }

}
