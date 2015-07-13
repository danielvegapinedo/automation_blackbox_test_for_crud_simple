package ca.danielvega.learning.seleniumforcrudsimple.specialTests.grid;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GridSearchTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        GridDevicePage.goTo();
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can search and filter a device.",
            description = "Try to search and filter a device using search components.",
            steps = {
                "Create a device.",
                "Go to Grid page and search the device using its name.",
                "Assert = Grid must show only one device.",
                "Assert = Check if the newly device is in the Grid."
            }
    )
    public void canSearchDevice() {
        DeviceCreator.createDevice();

        GridDevicePage.searchDevice(DeviceCreator.getPreviousName());
        assertTrue("The current stotred device is not 1", GridDevicePage.currentStoredDevices() == 1);
        assertTrue("Cannot search device.", GridDevicePage.doesDeviceExistInCurrentPageWithName(DeviceCreator.getPreviousName()));

    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can clean the field of search components.",
            description = "Try to clean the input field of the search components.",
            steps = {
                "Go to the Grid and check the device count.",
                "Create a new Device.",
                "Go to the Grid and search the previous device, use the exact name.",
                "Assert = The device showed in the grid must be only one.",
                "Remove the values of the serach input field and click in the button search.",
                "Assert = The current stored device must be equals to the step 1"

            }
    )
    public void canCleanSearchDevice() {
        GridDevicePage.storeDeviceCount();

        DeviceCreator.createDevice();

        GridDevicePage.searchDevice(DeviceCreator.getPreviousName());
        assertTrue("The current stored device is not 1", GridDevicePage.currentStoredDevices() == 1);

        GridDevicePage.cleanSearch();
        assertTrue("The current stored device is not correct.", GridDevicePage.currentStoredDevices() == GridDevicePage.previousStoredDevices() + 1);

    }
}
