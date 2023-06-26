package org.ToolRentApp.tools;

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
     * @param toolCode the tool code
     * @return Tool associated with the code
     */
    public Tool getTool(String toolCode) {
        if (!toolCodeToToolMap.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code.");
        }

        return toolCodeToToolMap.get(toolCode);
    }
}