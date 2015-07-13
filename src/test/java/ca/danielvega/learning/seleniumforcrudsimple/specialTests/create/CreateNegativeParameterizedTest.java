package ca.danielvega.learning.seleniumforcrudsimple.specialTests.create;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Recorder;
import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateNegativeParameterizedTest extends CrudSimpleTest {

    private final String name;
    private final String status;
    private final String mode;
    private final String type;
    private final String tariff;
    private final String customer;
    private final String expected;
    private final String filedName;

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method
    public CreateNegativeParameterizedTest(String name, String status,
            String mode, String type,
            String tariff, String customer, String expected, String filedName) {
        this.name = name;
        this.status = status;
        this.mode = mode;
        this.type = type;
        this.tariff = tariff;
        this.customer = customer;
        this.expected = expected;
        this.filedName = filedName;

    }

    @Before
    public void initialize() {
        if (CreateDevicePage.isAlertErrorPresent()) {
            CreateDevicePage.closeAlert();
        }
        CreateDevicePage.goTo();
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create several devices.",
            description = "Create several devices.",
            steps = {
                "Go to Create page.",
                "Create a device.",
                "Assert = The browser must show the Update page."
            }
    )
    @Recorder
    public void canValidateMinLengthRangeOfTheName() {
        
        report.log(LogStatus.INFO, "Creation of device with empty " + filedName + ": '"
                + this.name + "', "
                + this.status + "', "
                + this.mode + "', "
                + this.type + "', "
                + this.tariff + "', '"
                + this.customer + "'"
        );
        CreateDevicePage.createDevice()
                .withName(this.name)
                .withStatus(this.status)
                .withMode(this.mode)
                .withType(this.type)
                .withTariff(this.tariff)
                .withCustomer(this.customer)
                .create();

        assertTrue("Create page cannot validate field: " + filedName, CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", expected, CreateDevicePage.getAlertErrorMessage());
        if (filedName.equalsIgnoreCase("tariff")) {
            CreateDevicePage.closeAlert();
            CreateDevicePage.makeScreenshot();
            assertFalse(true);
        }
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
            {"", "status12", "mode13", "type14", "tariff15", "customer16", "Field 'name' is required.", "name"},
            {"   ", "status12", "mode13", "type14", "tariff15", "customer16", "Field 'name' is required.", "name"},
            {"name21", "", "mode23", "type24", "tariff25", "customer26", "Field 'status' is required.", "status"},
            {"name21", "   ", "mode23", "type24", "tariff25", "customer26", "Field 'status' is required.", "status"},
            {"name31", "status12", "", "type34", "tariff35", "customer36", "Field 'mode' is required.", "mode"},
            {"name31", "status12", "   ", "type34", "tariff35", "customer36", "Field 'mode' is required.", "mode"},
            {"name41", "status42", "mode43", "", "tariff45", "customer46", "Field 'type' is required.", "type"},
            {"name41", "status42", "mode43", "   ", "tariff45", "customer46", "Field 'type' is required.", "type"},
            {"name51", "status52", "mode53", "type54", "", "customer56", "Field 'tariff' is required.", "tariff"},
            {"name41", "status42", "mode43", "type54", "    ", "customer46", "Field 'tariff' is required.", "tariff"},
            {"name61", "status62", "mode63", "type64", "tariff65", "", "Field 'customer' is required.", "customer"},
            {"name61", "status62", "mode63", "type64", "tariff65", "   ", "Field 'customer' is required.", "customer"}

        });
    }

}
