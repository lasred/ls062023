package org.ToolRentApp.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ToolRentApp.tools.Tool;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.text.TextStringBuilder;


@AllArgsConstructor
public class RentalAgreement {

    private String toolCode;
    private Tool tool;
    private int rentalDayCount;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private BigDecimal costBeforeDiscount;
    private int discountPercentage;
    private BigDecimal discount;

    @Getter
    private BigDecimal finalCharge;

    /**
     * Generate string representation of RentalAgreement
     *
     * @return rental agreement
     */
    public String toString() {
        TextStringBuilder stringBuilder = new TextStringBuilder();
        stringBuilder.appendln("Tool Code: " + toolCode);
        stringBuilder.appendln("Tool Type: " + tool.getToolChargeInfo().toString().toLowerCase());
        stringBuilder.appendln("Tool Brand: " + tool.getBrand());
        stringBuilder.appendln("Rental Days: " + rentalDayCount);
        stringBuilder.appendln("Checkout Day: " + checkoutDate);
        stringBuilder.appendln("Due Date" + dueDate);
        stringBuilder.appendln("Daily Rental Charge: " + tool.getToolChargeInfo().getDailyCharge());
        stringBuilder.appendln("Charge Days: "  + chargeDays);
        stringBuilder.appendln("Pre discount charge: " + costBeforeDiscount);
        stringBuilder.appendln("Discount percent: " + discountPercentage + "%");
        stringBuilder.appendln("Discount amount: " + discount);
        stringBuilder.appendln("Final charge: " + finalCharge);

       return stringBuilder.toString();
    }
}