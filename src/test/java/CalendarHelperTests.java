
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.ToolRentApp.helpers.CalendarHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.time.Month;

public class CalendarHelperTests {
    private CalendarHelper calendarHelper;

    @BeforeEach
    public void init() {
        calendarHelper = new CalendarHelper();
    }

    @Test
    public void testBasicWeekday() {
        LocalDate localDate = LocalDate.of(2023, Month.JUNE, 26);
        assertTrue(calendarHelper.isWeekday(localDate));
        assertFalse(calendarHelper.isWeekend(localDate));

    }

    @Test
    public void testBasicWeekend() {
        LocalDate localDate = LocalDate.of(2023, Month.JUNE, 10);
        assertTrue(calendarHelper.isWeekend(localDate));
        assertFalse(calendarHelper.isWeekday(localDate));
    }

    @Test
    public void testLaborDay() {
        LocalDate localDate = LocalDate.of(2023, Month.SEPTEMBER, 4);
        assertTrue(calendarHelper.isHoliday(localDate));
        LocalDate localDate2 = LocalDate.of(2023, Month.SEPTEMBER, 3);
        assertFalse(calendarHelper.isHoliday(localDate2));
    }

    @Test
    public void testIndependenceDayOnWeekday(){
        LocalDate localDate = LocalDate.of(2023, Month.JULY, 4);
        assertTrue(calendarHelper.isHoliday(localDate));
        LocalDate localDate2 = LocalDate.of(2023, Month.JULY, 3);
        assertFalse(calendarHelper.isHoliday(localDate2));
    }

    @Test
    public void testIndependenceDayObservedOn5th() {
        LocalDate localDate = LocalDate.of(2021, Month.JULY, 4);
        assertFalse(calendarHelper.isHoliday(localDate));
        LocalDate localDate2 = LocalDate.of(2021, Month.JULY, 5);
        assertTrue(calendarHelper.isHoliday(localDate2));
    }

    @Test
    public void testIndependenceDayObservedOn3rd() {
        LocalDate localDate = LocalDate.of(2026, Month.JULY, 3);
        assertTrue(calendarHelper.isHoliday(localDate));
        LocalDate localDate2 = LocalDate.of(2026, Month.JULY, 4);
        assertFalse(calendarHelper.isHoliday(localDate2));
    }
}
