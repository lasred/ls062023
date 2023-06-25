package org.RentalAgreement;

import java.math.BigDecimal;
import lombok.Getter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tool {

    @Getter
    private String brand;

    @Getter
    private ToolChargeInfo toolChargeInfo;

}
