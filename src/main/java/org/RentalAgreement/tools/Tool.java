package org.RentalAgreement.tools;

import lombok.Getter;

import lombok.AllArgsConstructor;
import org.RentalAgreement.tools.ToolChargeInfo;

@AllArgsConstructor
public class Tool {

    @Getter
    private String brand;

    @Getter
    private ToolChargeInfo toolChargeInfo;

}
