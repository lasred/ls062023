package org.ToolRentApp;
import com.google.inject.Injector;
import com.google.inject.Guice;
import modules.AppModule;
import org.ToolRentApp.rental.RentalManager;
import org.ToolRentApp.rental.RentalAgreement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        RentalManager rentalManager = injector.getInstance(RentalManager.class);

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the tool code:");
        String toolCode = scan.nextLine();

        System.out.println("Enter a rental date in format of MM-dd-yyyy:");
        String rentalDateAsString = scan.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate rentalDate = LocalDate.parse(rentalDateAsString, dtf);

        System.out.println("Enter the number of days to rent tool:");
        int rentalDays = scan.nextInt();

        System.out.println("Enter the discount percentage as a whole number:");
        int discountPercentage = scan.nextInt();


        RentalAgreement agreement = rentalManager.rentTool(toolCode, discountPercentage, rentalDate, rentalDays);
        System.out.println(agreement);


    }
}