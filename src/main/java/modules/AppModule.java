package modules;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.ToolRentApp.helpers.CalendarHelper;
import org.ToolRentApp.rental.RentalManager;
import org.ToolRentApp.tools.Tool;
import org.ToolRentApp.tools.ToolChargeInfo;
import org.ToolRentApp.tools.ToolManager;

import java.util.HashMap;
import java.util.Map;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Named("ToolConfiguration")
    public Map<String, Tool> getToolConfiguration() {
       Map<String, Tool> toolConfig = new HashMap<String, Tool>();

        toolConfig.put("CHNS", new Tool("Stihl", ToolChargeInfo.CHAINSAW));
        toolConfig.put("LADW", new Tool("Werner", ToolChargeInfo.LADDER));
       toolConfig.put("JAKD", new Tool("DeWalt", ToolChargeInfo.JACKHAMMER));
       toolConfig.put("JAKR", new Tool("Ridgid", ToolChargeInfo.JACKHAMMER));

        return toolConfig;
    }

    @Provides
    public ToolManager getToolManager(@Named("ToolConfiguration") Map<String, Tool> toolCodeToToolMap) {
        return new ToolManager(toolCodeToToolMap);
    }

    @Provides
    public CalendarHelper getCalendarHelper() {
        return new CalendarHelper();
    }

    @Provides
    public RentalManager getRentalManager(ToolManager toolManager, CalendarHelper calendarHelper) {
        RentalManager rm = new RentalManager(toolManager, calendarHelper);
        return rm;
    }
}
