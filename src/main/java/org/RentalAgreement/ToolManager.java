package org.RentalAgreement;

import java.math.BigDecimal;
import java.util.Map;

public class ToolManager {

    //code
    //for O(1) lookup by access code (if many tools)
    //DI this-
    private Map<String, Tool> toolCodeToToolMap = initializeToolCodetoToolMap();



    private static Map<String, String> toolMap;
    //Where to initialize existing tools? - static map

    private Map<String, Tool> initializeToolCodetoToolMap() {

        return null;
    }

    /**
     * Retrieve a tool based on the code
     *
     * @param toolCode
     * @return
     */
    public Tool getTool(String toolCode) {
        if (!toolCodeToToolMap.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code");
        }

        return toolCodeToToolMap.get(toolCode);
    }
}