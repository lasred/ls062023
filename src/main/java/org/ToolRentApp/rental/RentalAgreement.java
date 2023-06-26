package org.ToolRentApp.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ToolRentApp.tools.Tool;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.commons.lang3.text.StrBuilder;


@AllArgsConstructor
public class RentalAgreement {

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
        StringBuilder stringBuilder = new StringBuilder();
        //update this
       return "";
    }
}