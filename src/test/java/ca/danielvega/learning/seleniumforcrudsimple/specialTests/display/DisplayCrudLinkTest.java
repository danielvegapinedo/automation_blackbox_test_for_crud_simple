package ca.danielvega.learning.seleniumforcrudsimple.specialTests.display;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.DisplayDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.UpdateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class DisplayCrudLinkTest  extends CrudSimpleTest {

    @BeforeClass
    public static void initializeClass() {
        DeviceCreator.createDevice();
    }
    @Before
    public void initialize() {
        DisplayDevicePage.goTo(DeviceCreator.getPreviousName());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Display can go to Grid page.",
            description = "From the Display top menu, try to go to Grid page.",
            steps = {                
                "Create a device.",
                "Go to the Display page of the newly device.",
                "Click in the Grid button of the top menu.",
                "Assert = The browser must show the Grid page."
            }
    )
    public void canGoToGridPage() {
        DisplayDevicePage.Navegation.Grid.select();
        assertTrue("Cannot go to Grid page.", GridDevicePage.isAt());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Display can go to Update page.",
            description = "From the Display top menu, try to go to Update page.",
            steps = {                
                "Create a device.",
                "Go to the Display page of the newly device.",
                "Click in the Update button of the top menu.",
                "Assert = The browser must show the Update page.",
                "Assert = The Update page must be of the newly device."
            }
    )
    public void canGoToUpdatePage() {
        DisplayDevicePage.Navegation.Update.select();
        assertTrue("Cannot go to Update page.", UpdateDevicePage.isAt());
        assertEquals("Update page is not correct.", DeviceCreator.getPreviousName(), UpdateDevicePage.getDeviceName());
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Display can go to Create page.",
            description = "From the Display top menu, try to go to Create page.",
            steps = {                
                "Create a device.",
                "Go to the Display page of the newly device.",
                "Click in the Create button of the top menu.",
                "Assert = The browser must show the Create page."
            }
    )
    public void canGoToCreatePage() {
        DisplayDevicePage.Navegation.Create.select();
        assertTrue("Cannot go to Create page.", CreateDevicePage.isAt());
    }





}