package ca.danielvega.learning.seleniumforcrudsimple.specialTests.update;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UpdateTest extends CrudSimpleTest {

    @BeforeClass
    public static void initializeClass() {
        DeviceCreator.createDevice();
    }

    @Before
    public void initialize() {
        UpdateDevicePage.goTo(DeviceCreator.getPreviousName());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Jump to Update.",
            description = "can go to Update page after update.",
            steps = {
                "Create a device.",
                "Go to Update page of the newly device.",
                "Update each field of the device.",
                "Assert = The browser must show the Update page."
            }
    )
    public void canGoToUpdatePageAfterOfTheUpdate() {

        String date = new Date().getTime() + "";
        String status = "status" + date, mode = "mode" + date, type = "type" + date, tariff = "tariff" + date, customer = "customer" + date;
        UpdateDevicePage.updateDevice()
                .withStatus(status)
                .withMode(mode)
                .withType(type)
                .withTariff(tariff)
                .withCustomer(customer)
                .update();

        assertTrue("Cannot go to Update page after of the update", UpdateDevicePage.isAt());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Validate edited values.",
            description = "Modified values match after update the device.",
            steps = {
                "Create a device.",
                "Go to Update page of the newly device.",
                "Update each field of the device.",
                "Assert = The device fileds are updated with the correct value."
            }
    )
    public void valuesOfUpdatedFieldsMatchWithSentUpdateFields() {

        String date = new Date().getTime() + "";
        String status = "status" + date, mode = "mode" + date, type = "type" + date, tariff = "tariff" + date, customer = "customer" + date;
        UpdateDevicePage.updateDevice()
                .withStatus(status)
                .withMode(mode)
                .withType(type)
                .withTariff(tariff)
                .withCustomer(customer)
                .update();

        assertTrue("Value of updated fields didn't match with sent update fields.", UpdateDevicePage.createMatch()
                .withStatus(status)
                .withMode(mode)
                .withType(type)
                .withTariff(tariff)
                .withCustomer(customer)
                .match());
    }

}
