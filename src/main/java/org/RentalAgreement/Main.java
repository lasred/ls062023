package org.RentalAgreement;
import lombok.*;
import com.google.inject.Injector;
import com.google.inject.Guice;
import modules.ToolModule;
import org.RentalAgreement.tools.Tool;
import org.RentalAgreement.tools.ToolManager;

public class Main {
    /**
     * Finish business logic
     * Add tests
     * Lombok
     *
     * @param args
     */


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ToolModule());
        ToolManager toolManager = injector.getInstance(ToolManager.class);
        Tool tool = toolManager.getTool("HNS");
        //Scanner that reads in data
        //From data generate a Rental agreement
        System.out.println(tool.getBrand());
        System.out.println("additional");
        //RentalAgreement agreement = new RentalAgreement();
    }
}