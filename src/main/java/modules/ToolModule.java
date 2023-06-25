package modules;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.RentalAgreement.Tool;

import java.util.Map;

public class ToolModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    public Map<String, Tool> getToolConfiguration() {
       return null;
    }
}
