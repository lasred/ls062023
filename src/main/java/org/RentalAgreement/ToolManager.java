package org.RentalAgreement;

import java.math.BigDecimal;
import java.util.Map;
import com.google.inject.Inject;
import com.google.inject.name.Named;


public class ToolManager {

    private Map<String, Tool> toolCodeToToolMap;


    @Inject
    public ToolManager(@Named("ToolConfiguration") Map<String, Tool> toolCodeToToolMap) {
       this.toolCodeToToolMap = toolCodeToToolMap;
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