package modules;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.RentalAgreement.Tool;

import java.util.HashMap;
import java.util.Map;

public class ToolModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Named("ToolConfiguration")
    public Map<String, Tool> getToolConfiguration() {
       Map<String, Tool> toolConfig = new HashMap<String, Tool>();


        return toolConfig;
    }
}
