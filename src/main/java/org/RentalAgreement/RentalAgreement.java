package org.RentalAgreement;
import org.RentalAgreement.ToolManager.Tool;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private BigDecimal finalCharge;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        //update this
       return "";
    }
}