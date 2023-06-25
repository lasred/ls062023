package org.RentalAgreement.tools;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ToolChargeInfo {
    LADDER(new BigDecimal(1.99), true, true, false),
    CHAINSAW(new BigDecimal(1.49), true, false, true),
    JACKHAMMER(new BigDecimal(2.99), true, false, false);

    @Getter
    private BigDecimal dailyCharge;
    @Getter
    private boolean weekdayCharged;
    @Getter
    private boolean weekendCharged;
    @Getter
    private boolean holidayCharged;

}
