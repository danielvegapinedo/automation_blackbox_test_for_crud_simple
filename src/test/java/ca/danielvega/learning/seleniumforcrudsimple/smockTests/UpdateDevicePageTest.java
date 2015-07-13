package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.experimental.categories.Category;

public class UpdateDevicePageTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        DeviceCreator.createDevice();
        UpdateDevicePage.goTo(DeviceCreator.getPreviousName());
    }

    @Test
    @Category(SmockTests.class)
    @Explanation(
            title = "Can edit full device.",
            description = "Can edit full device.",
            steps = {
                "Create a device.",
                "Go to Update page of the newly device.",
                "Edit device using valid values for all fields.",
                "Go to the Grid.",
                "Assert = The device exist in the Grid page. 9Search it.",
                "Click in the Grid navegation update button.",
                "Assert = Data in the fields are equal to step 3"

            }
    )
    public void canEditFullDevice() {
        String suffix = new Date() + "", status = "status_" + suffix, mode = "mode_" + suffix, type = "type_" + suffix, tariff = "tariff_" + suffix, customer = "customer_" + suffix;

        UpdateDevicePage.updateDevice()
                .withStatus(status)
                .withMode(mode)
                .withType(type)
                .withTariff(tariff)
                .withCustomer(customer)
                .update();

        GridDevicePage.goTo();
        assertTrue("New device is not in grid.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));
        GridDevicePage.Navegation.Update.select(DeviceCreator.getPreviousName());

        assertTrue("Value of updated fields didn't match with sent update fields.", UpdateDevicePage.createMatch()
                .withStatus(status)
                .withMode(mode)
                .withType(type)
                .withTariff(tariff)
                .withCustomer(customer)
                .match());

    }

}
