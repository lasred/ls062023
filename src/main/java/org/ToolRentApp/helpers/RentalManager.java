package org.ToolRentApp.helpers;

import org.ToolRentApp.tools.Tool;
import org.ToolRentApp.tools.ToolChargeInfo;
import org.ToolRentApp.tools.ToolManager;
import org.ToolRentApp.rental.RentalAgreement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Rental manager - Handles/services request to rent tools
 */
public class RentalManager {

    private ToolManager toolManager;

    private CalendarHelper calendarHelper;

    /**
     * RentalManager - handles/services request to rent tools
     *
     * @param toolManager the tool manager
     * @param calendarHelper the calendar helper
     */
    public RentalManager(ToolManager toolManager, CalendarHelper calendarHelper) {
        this.toolManager = toolManager;
        this.calendarHelper = calendarHelper;
    }

    /**
     * rentTool - rent a tool and issue a rental agreement
     *
     * @param toolCode the tool code
     * @param discountPercentage percentage of discount in whole number
     * @param checkoutDate the check out date
     * @param rentalDayCount how many days the tool will be rented out for
     * @return A rental agreement that includes billing information
     */
    public RentalAgreement rentTool(String toolCode, int discountPercentage, LocalDate checkoutDate, int rentalDayCount) {
        if(discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Invalid discount percentage. Must be between 0 and 100.");
        }

        if(rentalDayCount <= 0) {
            throw new IllegalArgumentException("Rental day count must be greater than 0.");
        }

        Tool tool = toolManager.getTool(toolCode);
        ToolChargeInfo toolChargeInfo = tool.getToolChargeInfo();

        int chargeDays = getChargeDays(tool, checkoutDate, rentalDayCount);
        BigDecimal costBeforeDiscount = toolChargeInfo.getDailyCharge().multiply(new BigDecimal(chargeDays)).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal discountP = new BigDecimal(discountPercentage/100.0);
        BigDecimal discount = costBeforeDiscount.multiply(discountP).setScale(2, RoundingMode.HALF_EVEN);

        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);

        BigDecimal finalCharge = costBeforeDiscount.subtract(discount).setScale(2, RoundingMode.HALF_EVEN);

        RentalAgreement rentalAgreement = new RentalAgreement(toolCode, tool, rentalDayCount, checkoutDate, dueDate, chargeDays,
                costBeforeDiscount, discountPercentage, discount, finalCharge);

        return rentalAgreement;
    }

    private int getChargeDays(Tool tool, LocalDate checkoutDate, int rentalDayCount) {

        int chargeDays = 0;
        //start at date after checkout date
        for(int i=1; i<= rentalDayCount; i++) {

            LocalDate toEvaluate = checkoutDate.plusDays(i);

            if(shouldCharge(tool.getToolChargeInfo(), toEvaluate)) {
                chargeDays ++;
            }
        }
        return chargeDays;
    }

    private boolean shouldCharge(ToolChargeInfo tool, LocalDate date) {

        //if holiday -> should override weekday charge
        if(calendarHelper.isHoliday(date) && !tool.isHolidayCharged()) {
            return false;
        }

        if(calendarHelper.isWeekend(date) && !tool.isWeekendCharged()) {
            return false;
        }

        if(calendarHelper.isWeekday(date) && !tool.isWeekdayCharged()) {
            return false;
        }

        return true;

    }
}