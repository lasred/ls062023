package org.RentalAgreement.helpers;

import org.RentalAgreement.tools.Tool;
import org.RentalAgreement.tools.ToolChargeInfo;
import org.RentalAgreement.tools.ToolManager;
import org.RentalAgreement.rental.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        ToolChargeInfo toolChargeInfo = tool.getToolChargeInfo();

        int chargeDays = getChargeDays(tool, checkoutDate, rentalDayCount);
        BigDecimal costBeforeDiscount = toolChargeInfo.getDailyCharge().multiply(new BigDecimal(chargeDays));

        BigDecimal discountP = new BigDecimal(discountPercentage/100.0);
        BigDecimal discount = costBeforeDiscount.multiply(discountP);
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

        if(calendarHelper.isWeekend(date) && tool.isWeekdayCharged()) {
            return false;
        }

        return true;

    }
}