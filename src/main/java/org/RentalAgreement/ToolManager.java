package org.RentalAgreement;

import java.math.BigDecimal;
import java.util.Map;

public class ToolManager {

    //code
    //for O(1) lookup by access code (if many tools)
    private Map<String, Tool> toolCodeToToolMap = initializeToolCodetoToolMap();

    class Tool {
        //ChargeType
        String brand;
        BigDecimal dailyCharge;
        boolean weekendCharge;
        boolean holidayCharge;
        boolean weekdayCharge;
    }


    private static Map<String, String> toolMap;
    //Where to initialize existing tools? - static map

    private Map<String, Tool> initializeToolCodetoToolMap() {

        return null;
    }

    public Tool getTool(String toolCode) {
        if (!toolCodeToToolMap.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        return toolCodeToToolMap.get(toolCode);
    }
}