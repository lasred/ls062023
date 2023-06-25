package org.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.RentalAgreement.ToolManager.Tool;

public class RentalManager {

    private ToolManager toolManager;

    private CalendarHelper calendarHelper;

    /**
     *
     * @param toolManager
     */
    public RentalManager(ToolManager toolManager) {
        this.toolManager = toolManager;
    }

    /**
     * rentTool - rent a tool and issue a rental agreement
     * @param toolCode
     * @param discountPercentage
     * @param checkoutDate
     * @param rentalDayCount
     * @return A rental agreement
     */
    public RentalAgreement rentTool(String toolCode, int discountPercentage, LocalDate checkoutDate, int rentalDayCount) {
        if(discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
        }

        Tool tool = toolManager.getTool(toolCode);

        int chargeDays = getChargeDays(tool, checkoutDate, rentalDayCount);
        BigDecimal costBeforeDiscount = tool.dailyCharge.multiply(new BigDecimal(chargeDays));

        BigDecimal discountP = new BigDecimal(discountPercentage/100.0);
        BigDecimal discount = tool.dailyCharge.multiply(discountP);
        //might need to add 1 here
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);

        BigDecimal finalCharge = costBeforeDiscount.subtract(discount);


        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDayCount, checkoutDate, dueDate, chargeDays,
                costBeforeDiscount, discountPercentage, discount, finalCharge);

        return rentalAgreement;
    }

    /**
     *
     * @param tool
     * @param checkoutDate
     * @param rentalDayCount
     * @return
     */
    private int getChargeDays(Tool tool, LocalDate checkoutDate, int rentalDayCount) {

        int chargeDays = 0;
        //start at date after checkout date
        for(int i=1; i<= rentalDayCount; i++) {

            LocalDate toEvaluate = checkoutDate.plusDays(i);

            if(shouldCharge(tool, toEvaluate)) {
                chargeDays ++;
            }
        }
        return chargeDays;
    }

    private boolean shouldCharge(Tool tool, LocalDate date) {

        //if holiday -> should override weekday charge
        if(calendarHelper.isHoliday(date) && !tool.holidayCharge) {
            return false;
        }

        if(calendarHelper.isWeekend(date) && !tool.weekendCharge) {
            return false;
        }

        if(calendarHelper.isWeekend(date) && tool.weekdayCharge) {
            return false;
        }

        return true;

    }
}