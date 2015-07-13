package ca.danielvega.learning.seleniumforcrudsimple.specialTests.update;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UpdateNegativeTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        GridDevicePage.goTo();
        UpdateDevicePage.goTo(GridDevicePage.getFirstDeviceName());
    }

    @After
    public void cleanup() {
        if (UpdateDevicePage.isAlertErrorPresent()) {
            UpdateDevicePage.closeAlert();
        }
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Negative test of Status field.",
            description = "Try to add an empty value for Status field.",
            steps = {
                "Go to Grid and click in the Update button of some device.",
                "Update the device, use valid values for each field and leave empty the Status field.",
                "Assert = The browser must show an Alert error message."
            }
    )
    public void canValidateRequeridStatus() {
        UpdateDevicePage.updateDevice()
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .update();

        assertTrue("Cannot validate of required Status.", UpdateDevicePage.isAlertErrorPresent());
        report.log(LogStatus.PASS, "Alert message" + report.addScreenCapture(Driver.takesAlertScreenshot().getAbsolutePath()));
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Negative test of Mode field.",
            description = "Try to add an empty value for Mode field.",
            steps = {
                "Go to Grid and click in the Update button of some device.",
                "Update the device, use valid values for each field and leave empty the Mode field.",
                "Assert = The browser must show an Alert error message."
            }
    )
    public void canValidateRequeridMode() {
        UpdateDevicePage.updateDevice()
                .withStatus("Status")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .update();
        assertTrue("Cannot validate of required Mode.", UpdateDevicePage.isAlertErrorPresent());
        report.log(LogStatus.PASS, "Alert message" + report.addScreenCapture(Driver.takesAlertScreenshot().getAbsolutePath()));
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Negative test of Type field.",
            description = "Try to add an empty value for Type field.",
            steps = {
                "Go to Grid and click in the Update button of some device.",
                "Update the device, use valid values for each field and leave empty the Type field.",
                "Assert = The browser must show an Alert error message."
            }
    )
    public void canValidateRequeridType() {
        UpdateDevicePage.updateDevice()
                .withStatus("Status")
                .withMode("Mode")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .update();
        assertTrue("Cannot validate of required Type.", UpdateDevicePage.isAlertErrorPresent());
        report.log(LogStatus.PASS, "Alert message" + report.addScreenCapture(Driver.takesAlertScreenshot().getAbsolutePath()));
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Negative test of Tariff field.",
            description = "Try to add an empty value for Tariff field.",
            steps = {
                "Go to Grid and click in the Update button of some device.",
                "Update the device, use valid values for each field and leave empty the Tariff field.",
                "Assert = The browser must show an Alert error message."
            }
    )
    public void canValidateRequeridTariff() {
        UpdateDevicePage.updateDevice()
                .withStatus("Status")
                .withMode("Mode")
                .withType("Typee")
                .withCustomer("Customer")
                .update();
        assertTrue("Cannot validate of required Tariff.", UpdateDevicePage.isAlertErrorPresent());
        report.log(LogStatus.PASS, "Alert message" + report.addScreenCapture(Driver.takesAlertScreenshot().getAbsolutePath()));
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Negative test of Customer field.",
            description = "Try to add an empty value for Customer field.",
            steps = {
                "Go to Grid and click in the Update button of some device.",
                "Update the device, use valid values for each field and leave empty the Customer field.",
                "Assert = The browser must show an Alert error message."
            }
    )
    public void canValidateRequeridCustomer() {
        UpdateDevicePage.updateDevice()
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .update();
        assertTrue("Cannot validate of required Customer.", UpdateDevicePage.isAlertErrorPresent());
        report.log(LogStatus.PASS, "Alert message" + report.addScreenCapture(Driver.takesAlertScreenshot().getAbsolutePath()));
    }
}
