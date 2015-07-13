package ca.danielvega.learning.seleniumforcrudsimple.specialTests.create;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CreateNegativeTest extends CrudSimpleTest {

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
            title = "Create page can validate Name min range.",
            description = "Create page valid the rango of the Name field. It must be between 2 and 20 characters.",
            steps = {
                "Go to Create page.",
                "Create a device using a single character as Name",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'name' is must have between 2 and 20 characters.\"",}
    )
    public void canValidateMinLengthRangeOfTheName() {
        CreateDevicePage.createDevice()
                .withName("A")
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();

        assertTrue("Cannot validate min length range of the Name .", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'name' is must have between 2 and 20 characters.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate Name using max range.",
            description = "Create page valid the range of the Name field. It must be between 2 and 20 characters.",
            steps = {
                "Go to Create page.",
                "Create a device using 21 characters string as Name",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'name' is must have between 2 and 20 characters.\"",}
    )
    public void canValidateMaxLengthRangeOfTheName() {
        CreateDevicePage.createDevice()
                .withName("01234567890123456789x")
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        assertTrue("Cannot validate max range of the Name .", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'name' is must have between 2 and 20 characters.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Name field.",
            description = "Create page valids the requerid Name field.",
            steps = {
                "Go to Create page.",
                "Create a device without Name",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'name' is required.\"",}
    )
    public void doValidationOfRequeridName() {
        CreateDevicePage.createDevice()
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        assertTrue("Create page did not valid the requerid Name field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'name' is required.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Status field.",
            description = "Create page valids the requerid Status field.",
            steps = {
                "Go to Create page.",
                "Create a device without Status",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'status' is required.\"",}
    )
    public void doValidationOfRequeridStatus() {
        CreateDevicePage.createDevice()
                .withName("Name")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        assertTrue("Create page did not valid the requerid Status field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'status' is required.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Mode field.",
            description = "Create page valids the requerid Mode field.",
            steps = {
                "Go to Create page.",
                "Create a device without Status",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'mode' is required.\"",}
    )
    public void doValidationOfRequeridMode() {
        CreateDevicePage.createDevice()
                .withName("Name")
                .withStatus("Status")
                .withType("Type")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        assertTrue("Create page did not valid the requerid Mode field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'mode' is required.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Type field.",
            description = "Create page valids the requerid Type field.",
            steps = {
                "Go to Create page.",
                "Create a device without Status",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'type' is required.\"",}
    )
    public void doValidationOfRequeridType() {
        CreateDevicePage.createDevice()
                .withName("Name")
                .withStatus("Status")
                .withMode("Mode")
                .withTariff("Tariff")
                .withCustomer("Customer")
                .create();
        assertTrue("Create page did not valid the requerid Type field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'type' is required.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Tariff field.",
            description = "Create page valids the requerid Tariff field.",
            steps = {
                "Go to Create page.",
                "Create a device without Status",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'tariff' is required.\"",}
    )
    public void doValidationOfRequeridTariff() {
        CreateDevicePage.createDevice()
                .withName("Name")
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withCustomer("Customer")
                .create();
        assertTrue("Create page did not valid the requerid Tariff field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'tariff' is required.", CreateDevicePage.getAlertErrorMessage());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Create page can validate requerid Customer field.",
            description = "Create page valids the requerid Customer field.",
            steps = {
                "Go to Create page.",
                "Create a device without Customer",
                "Assert = The browser must show alert error message.",
                "Assert = The browser must show alert error message with the correct message : \"Field 'customer' is required.\"",}
    )
    public void doValidationOfRequeridCustomer() {
        CreateDevicePage.createDevice()
                .withName("Name")
                .withStatus("Status")
                .withMode("Mode")
                .withType("Type")
                .withTariff("Tariff")
                .create();
        assertTrue("Create page did not valid the requerid Customer field.", CreateDevicePage.isAlertErrorPresent());
        assertEquals("The alert error message is not correct.", "Field 'customer' is required.", CreateDevicePage.getAlertErrorMessage());
    }
}
