package ca.danielvega.learning.seleniumforcrudsimple.specialTests.grid;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.Address;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.DisplayDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GridCrudLinkTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        GridDevicePage.goTo();
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can go to Update page.",
            description = "From the  Grid Navegation buttons, try to go to Update page for a specific device.",
            steps = {
                "Go to the Grid page.",
                "Choice some device in the grid table and click in the Update button.",
                "Assert = the browser must show the Update page."
            }
    )
    public void canGoToUpdatePage() {
        String deviceName = GridDevicePage.getFirstDeviceName();
        GridDevicePage.Navegation.Update.select(deviceName);

        assertEquals("Can not go to Update page.", deviceName, UpdateDevicePage.getDeviceName());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can go to Display page.",
            description = "From the  Grid Navegation buttons, try to go to Display page for a specific device.",
            steps = {
                "Go to the Grid page.",
                "In the grid table, choice some device and click in the Dislay button.",
                "Assert = the browser must show the Display page."
            }
    )
    public void canGoToDisplayPage() {
        String deviceName = GridDevicePage.getFirstDeviceName();
        GridDevicePage.Navegation.Update.select(deviceName);

        assertEquals("Can not go to Display page.", deviceName, DisplayDevicePage.getDeviceName());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can go to Create page.",
            description = "From the  Grid Top Navegation menu, try to go to Create page for a specific device.",
            steps = {
                "Go to the Grid page.",
                "Click on the Create button of the Top navigation menu.",
                "Assert = the browser must show the Created page."
            }
    )
    public void canGoToCreatePage() {
        GridDevicePage.goFromTopNavigationTo(Address.CREATE);
        assertTrue("Cannot go to Create page.", CreateDevicePage.isAt());
    }

}
