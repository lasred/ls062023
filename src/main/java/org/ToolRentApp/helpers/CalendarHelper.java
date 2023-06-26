package org.ToolRentApp.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class CalendarHelper {

    /**
     * Return if the specified date falls on the weekend
     * @param localDate the specified date
     * @return specified date is on the weekend
     */
    public boolean isWeekend(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Return if the specified date is a weekday
     * @param localDate the specified date
     * @return specified date is a weekday
     */
    public boolean isWeekday(LocalDate localDate) {
        return !isWeekend(localDate);
    }


    /**
     * Return if the specified date is a date where a holiday is observed(current holidays are labor day and independence day)
     * @param localDate the specified date
     * @return specified date is a date where a holiday is observed
     */
    public boolean isHoliday(LocalDate localDate) {

        return isLaborDay(localDate) || isIndependeceDayObserved(localDate);
    }

    private boolean isLaborDay(LocalDate localDate) {
        //First Monday in September
        if(localDate.getMonth() != Month.SEPTEMBER) {
            return false;
        }

        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
        LocalDate firstInMonth = localDate.with(temporalAdjuster);

        return firstInMonth.getDayOfMonth() == localDate.getDayOfMonth();
    }

    private boolean isIndependeceDayObserved(LocalDate localDate) {
        if(localDate.getMonth() != Month.JULY) {
            return false;
        }

        //falls on weekday, observed on weekday
        if(localDate.getDayOfMonth() == 4 && isWeekday(localDate)) {
            return true;
        }

        //If falls on weekend, it is observed on the closest weekday (if Sat,
        //then Friday before),
        if(localDate.getDayOfMonth() == 3 && localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return true;
        }

        //If falls on weekend, it is observed on the closest weekday (,
        //if Sunday, then Monday after)
        if(localDate.getDayOfMonth() == 5 && localDate.getDayOfWeek() == DayOfWeek.MONDAY) {
            return true;
        }

        return false;
    }
}
