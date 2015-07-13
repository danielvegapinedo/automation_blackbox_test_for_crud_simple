package ca.danielvega.learning.seleniumforcrudsimple.crudLinkTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.CrudLinkTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.Address;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

public class UpdateDevicePageTest extends CrudSimpleTest {

    @Test
    @Category({FastTests.class, CrudLinkTests.class, SmockTests.class})
    @Explanation(
            title = "Can Update page link CRUD pages.",
            description = "Test the CRUD links in the Update page.",
            steps = {
                "Go to Update page.",
                "Click on Grid icon of top menu.",
                "Assert = Browser must show Grid page.",
                "Click on Display icon of top menu.",
                "Assert = Browser must show Display page.",
                "Click on Create icon of top menu.",
                "Assert = Browser must show Create page."
            }
    )
    public void linkToGrid() {
        assertTrue("Cannot link from update to crud pages", UpdateDevicePage.isLinkValid(Address.GRID, Address.DISPLAY, Address.CREATE));
    }
}
