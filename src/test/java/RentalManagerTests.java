import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.AppModule;
import org.ToolRentApp.helpers.CalendarHelper;
import org.ToolRentApp.helpers.RentalManager;
import org.ToolRentApp.rental.RentalAgreement;
import org.ToolRentApp.tools.Tool;
import org.ToolRentApp.tools.ToolChargeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RentalManagerTests {

    private RentalManager rentalManager;

    @BeforeEach
    public void init() {
        Injector injector = Guice.createInjector(new AppModule());
        rentalManager = injector.getInstance(RentalManager.class);
    }

    @Test
    public void testInvalidRentalDay() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalManager.rentTool("LADW", 0, LocalDate.now(), 0);
        });
        assertEquals(exception.getMessage(), "Rental day count must be greater than 0.");
    }

    @Test
    public void testInvalidTool() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalManager.rentTool("ZKSR", 0, LocalDate.now(), 11);
        });
        assertEquals(exception.getMessage(), "Invalid tool code.");
    }

    /*
       Scenario 1 -
       JAKR
       9/3/15
       5 days
       101% discount
     */
    @Test
    public void testScenario1InvalidPercentageScenario() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalManager.rentTool("JAKR", 101, LocalDate.of(2015, 9,3), 5);
        });
        assertEquals(exception.getMessage(), "Invalid discount percentage. Must be between 0 and 100.");
    }

    /*
     Scenario 2 -
     LADW
     7/2/20
     3 days
     25% discount
   */
    @Test
    public void testScenario2(){
        Tool expectedTool = new Tool("Werner", ToolChargeInfo.LADDER);
        int testRentalDays = 3;
        int discountPercentage = 10;
        LocalDate date =  LocalDate.of(2015, 7 , 2);
        LocalDate expectedDueDate = date.plusDays(testRentalDays);
        RentalAgreement expectedRentalAgreement = new RentalAgreement("LADW", expectedTool, testRentalDays,
                date, expectedDueDate, 2, new BigDecimal(3.98).setScale(2, RoundingMode.HALF_EVEN),
                discountPercentage, new BigDecimal(0.4).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(3.58).setScale(2, RoundingMode.HALF_EVEN));

        assertEquals(expectedRentalAgreement.toString(),
                rentalManager.rentTool("LADW", discountPercentage, date, testRentalDays).toString());
    }

    /*
     * Scenario 3
     * CHNS
     * 7/2/15
     * 5 days
     * 0% discount
     */
    @Test
    public void testScenario3(){
        Tool expectedTool = new Tool("Stihl", ToolChargeInfo.CHAINSAW);
        int testRentalDays = 5;
        int discountPercentage = 25;
        LocalDate date =  LocalDate.of(2015, 7 , 2);
        LocalDate expectedDueDate = date.plusDays(testRentalDays);
        RentalAgreement expectedRentalAgreement = new RentalAgreement("CHNS", expectedTool, testRentalDays,
                date, expectedDueDate, 3, new BigDecimal(4.47).setScale(2, RoundingMode.HALF_EVEN),
                discountPercentage, new BigDecimal(1.12).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(3.35).setScale(2, RoundingMode.HALF_EVEN));

        assertEquals(expectedRentalAgreement.toString(),
                rentalManager.rentTool("CHNS", discountPercentage, date, testRentalDays).toString());
    }

    /*
    Scenario 4
    JAKD
    9/3/15
    6 Days
    0% discount
     */
    @Test
    public void testScenario4(){
        Tool expectedTool = new Tool("DeWalt", ToolChargeInfo.JACKHAMMER);
        int testRentalDays = 6;
        int discountPercentage = 0;
        LocalDate date =  LocalDate.of(2015, 9 , 3);
        LocalDate expectedDueDate = date.plusDays(testRentalDays);
        RentalAgreement expectedRentalAgreement = new RentalAgreement("JAKD", expectedTool, testRentalDays,
                date, expectedDueDate, 3, new BigDecimal(8.97).setScale(2, RoundingMode.HALF_EVEN),
                discountPercentage, new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(8.97).setScale(2, RoundingMode.HALF_EVEN));

        assertEquals(expectedRentalAgreement.toString(),
                rentalManager.rentTool("JAKD", discountPercentage, date, testRentalDays).toString());
    }

    /*
    Scenario 5
    JAKR
    7/2/15
    9 Days
    0% Discount
     */
    @Test
    public void testScenario5(){
        Tool expectedTool = new Tool("Ridgid", ToolChargeInfo.JACKHAMMER);
        int testRentalDays = 9;
        int discountPercentage = 0;
        LocalDate date =  LocalDate.of(2015, 7 , 2);
        LocalDate expectedDueDate = date.plusDays(testRentalDays);
        RentalAgreement expectedRentalAgreement = new RentalAgreement("JAKR", expectedTool, testRentalDays,
                date, expectedDueDate, 5, new BigDecimal(14.95).setScale(2, RoundingMode.HALF_EVEN),
                discountPercentage, new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(14.95).setScale(2, RoundingMode.HALF_EVEN));

        assertEquals(expectedRentalAgreement.toString(),
                rentalManager.rentTool("JAKR", discountPercentage, date, testRentalDays).toString());
    }

    /*
       Scenario6
       JAKR
       7/2/20
       4 Days
       50% discount
     */
    @Test
    public void testScenario6(){
        Tool expectedTool = new Tool("Ridgid", ToolChargeInfo.JACKHAMMER);
        int testRentalDays = 4;
        int discountPercentage = 50;
        LocalDate date =  LocalDate.of(2020, 7 , 2);
        LocalDate expectedDueDate = date.plusDays(testRentalDays);
        RentalAgreement expectedRentalAgreement = new RentalAgreement("JAKR", expectedTool, testRentalDays,
                date, expectedDueDate, 1, new BigDecimal(2.99).setScale(2, RoundingMode.HALF_EVEN),
                discountPercentage, new BigDecimal(1.50).setScale(2, RoundingMode.HALF_EVEN),
                new BigDecimal(1.49).setScale(2, RoundingMode.HALF_EVEN));

        assertEquals(expectedRentalAgreement.toString(),
                rentalManager.rentTool("JAKR", discountPercentage, date, testRentalDays).toString());
    }

}
