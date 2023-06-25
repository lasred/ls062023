package org.RentalAgreement;

import java.math.BigDecimal;
import lombok.Getter;
public class Tool {
    private String brand;

    @Getter
    private BigDecimal dailyCharge;
    @Getter
    private boolean weekendCharged;
    @Getter
    private boolean holidayCharged;
    @Getter
    private boolean weekdayCharged;
}
