package ca.danielvega.learning.seleniumforcrudsimple.specialTests.create;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.RemoveDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreateTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        CreateDevicePage.goTo();
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can go to update.",
            description = "Create page can go to update page after device creation.",
            steps = {
                "Go to Create page.",
                "Create a device.",
                "Assert = The browser must show the Update page."
            }
    )
    public void canGoToUpdatePageAfterOfTheCreation() {
        String deviceName = "AAA_" + new Date().getTime();

        CreateDevicePage.createDevice()
                .withName(deviceName)
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();

        assertTrue("Cannot go to Update page after of the creation", UpdateDevicePage.isAt());

        RemoveDevicePage.remove(deviceName);
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create device correctly",
            description = "Create a device and check its values.",
            steps = {
                "Go to Create page.",
                "Create a device.",
                "Assert = After the creation the nupdate page must show the correct values."
            }
    )
    public void valuesOfCreationFieldsMatchWithUpdateFields() {
        String deviceName = "AAA_" + new Date().getTime();

        CreateDevicePage.createDevice()
                .withName(deviceName)
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();

        assertTrue("Value of creation fields didn't match with update fields.", UpdateDevicePage.createMatch()
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .match());

        RemoveDevicePage.remove(deviceName);
    }
    
}
