package org.ToolRentApp;
import com.google.inject.Injector;
import com.google.inject.Guice;
import modules.AppModule;
import org.ToolRentApp.tools.Tool;
import org.ToolRentApp.tools.ToolManager;

public class Main {
    /**
     * Finish business logic
     * Add tests
     * Lombok
     *
     * @param args
     */


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        ToolManager toolManager = injector.getInstance(ToolManager.class);
        Tool tool = toolManager.getTool("HNS");
        //Scanner that reads in data
        //From data generate a Rental agreement
        System.out.println(tool.getBrand());
        System.out.println("additional");
        System.out.println(tool.getToolChargeInfo().toString());

        //RentalAgreement agreement = new RentalAgreement();
    }
}