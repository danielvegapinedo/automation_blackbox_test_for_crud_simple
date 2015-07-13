package ca.danielvega.learning.seleniumforcrudsimple.specialTests.grid;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import com.relevantcodes.extentreports.LogStatus;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GridTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        GridDevicePage.goTo();
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can remove a device.",
            description = "Try to remove a  device from Grid page.",
            steps = {
                "Create a device.",
                "Go to grid.",
                "Remove the newly device.",
                "Assert = The Device is not longer in the grid."
            }
    )
    public void canRemoveOneDevice() {
        DeviceCreator.createDevice();

        GridDevicePage.goTo();
        GridDevicePage.removeDevice(DeviceCreator.getPreviousName());
        assertFalse("Grid cannot remove a device.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));
    }

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Grid can show up a new device.",
            description = "Try to create a new device and show up it in the Grid page.",
            steps = {
                "Go to the Grid page.",
                "Store the device count.",
                "Create a device.",
                "Go to grid.",
                "Assert = The device count is equals to the value stored in the step 2 more one.",
                "Assert = The Device must be in the grid. (serach it)",
                "Remove the device.",
                "Assert = The device count is equals to the value stored in the step 2."
            }
    )
    public void deviceShowUp() {
        DeviceCreator.cleanup();
        GridDevicePage.storeDeviceCount();

        DeviceCreator.createDevice();

        GridDevicePage.goTo();
        assertTrue("Created device was not in the grid count.", GridDevicePage.previousStoredDevices() + 1 == GridDevicePage.currentStoredDevices());
        report.log(LogStatus.INFO, "Device was created, previousStoredDevices: " + GridDevicePage.previousStoredDevices() + " currentStoredDevices: " + GridDevicePage.currentStoredDevices());

        assertTrue("Created device was not in the grid.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));
        report.log(LogStatus.PASS, "Device was in the Grid");

        GridDevicePage.removeDeviceUsingActions(DeviceCreator.getPreviousName());
        assertTrue("Removed device was not removed in the grid count.", GridDevicePage.previousStoredDevices() == GridDevicePage.currentStoredDevices());
        report.log(LogStatus.INFO, "Device was remove form the Grid");

    }

}
