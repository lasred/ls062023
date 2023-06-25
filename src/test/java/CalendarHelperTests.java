
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.RentalAgreement.helpers.CalendarHelper;
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
    }
}
