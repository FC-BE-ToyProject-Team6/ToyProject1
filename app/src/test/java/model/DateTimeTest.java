package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class DateTimeTest {


    private final int year = 2023;
    private final int month = 12;
    private final int day = 31;
    private final int hour = 12;
    private final int minute = 59;

    @Test
    public void 시간분이정상적으로입력되는가() {
        DateTime dateTime = new DateTime(year, month, day, hour, minute);
        assertThat(dateTime).isEqualTo(new DateTime(year, month, day, hour, minute));
    }

    @Test
    public void 문자열의파싱이정상적으로입력되는가() {
        DateTime dateTime = DateTime.ofString("2023-12-31 12:59");
        assertThat(dateTime).isEqualTo(new DateTime(year, month, day, hour, minute));
    }

    @Test
    public void 시간이0보다작으면오류처리() {
        assertThatThrownBy(() -> {
            new DateTime(year, month, day, -1, minute);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("시간은 0 ~ 23 사이 여야 합니다.");
    }

    @Test
    public void 시간이23보다크면오류처리() {
        assertThatThrownBy(() -> {
            new DateTime(year, month, day, 24, minute);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("시간은 0 ~ 23 사이 여야 합니다.");
    }

    @Test
    public void 분이0보다작으면오류처리() {
        assertThatThrownBy(() -> {
            new DateTime(year, month, day, hour, -1);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("분은 0~59 사이 여야 합니다.");
    }

    @Test
    public void 분이60보다크면오류처리() {
        assertThatThrownBy(() -> {
            new DateTime(year, month, day, hour, 60);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("분은 0~59 사이 여야 합니다.");
    }

    @Test
    public void DateTimeToString() {
        DateTime dateTime = DateTime.ofString("2020-12-12 13:14");
        assertThat(dateTime.toString()).isEqualTo("2020-12-12 13:14");
    }

}
