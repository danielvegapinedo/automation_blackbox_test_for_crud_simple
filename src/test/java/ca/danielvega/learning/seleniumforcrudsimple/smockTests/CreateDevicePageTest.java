package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.RemoveDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.experimental.categories.Category;

public class CreateDevicePageTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        CreateDevicePage.goTo();
    }

    @Test()
    @Category(SmockTests.class)
    @Explanation(
            title = "Create page can create a full device.",
            description = "Create a devce using all fields.",
            steps = {
                "Go to Create page.",
                "Create a device using all field with valid values.",
                "Assert = After the creation, the browser shows the update page.",
                "Assert = The browser shows the update page."
            }
    )
    public void createFullDevice() {
        String deviceName = "AAA_" + new Date().getTime();
        report.log(LogStatus.INFO, "Device Name", deviceName);

        CreateDevicePage.createDevice()
                .withName(deviceName)
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        report.log(LogStatus.PASS, "Device Created.", "The Device creation process finished fine.");

        assertTrue("After creation, the browser did not show the Update page.", UpdateDevicePage.isAt());
        report.log(LogStatus.PASS, "Automatic jump.", "After creation, the browser showed the Update page.");

        assertEquals("The Update page did not represent the created device.", deviceName, UpdateDevicePage.getDeviceName());
        report.log(LogStatus.PASS, "Validate fields.", "The Update page represented the created device." + report.addScreenCapture(Driver.takesScreenshot().getAbsolutePath()));

        RemoveDevicePage.remove(deviceName);

    }

}
