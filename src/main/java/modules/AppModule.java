package modules;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
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

       toolConfig.put("HNS", new Tool("Stihl", ToolChargeInfo.CHAINSAW));
       toolConfig.put("LADW", new Tool("Werner", ToolChargeInfo.LADDER));
       toolConfig.put("JAKD", new Tool("DeWalt", ToolChargeInfo.JACKHAMMER));
       toolConfig.put("JAKR", new Tool("Ridgid", ToolChargeInfo.JACKHAMMER));

        return toolConfig;
    }

    @Provides
    public ToolManager toolManager(@Named("ToolConfiguration") Map<String, Tool> toolCodeToToolMap) {
        System.out.println(toolCodeToToolMap.keySet());
        return new ToolManager(toolCodeToToolMap);
    }
}
