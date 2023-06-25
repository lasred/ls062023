package org.RentalAgreement;

import java.time.DayOfWeek;
import java.time.LocalDate;

//tests for these classes
public class CalendarHelper {

    //TODO - add tests for these
    public boolean isWeekend(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate localDate) {
        /**
         *
         */
        return false;
    }

    public boolean isWeekday(LocalDate localDate) {
        return !isWeekend(localDate);
    }
}
