package ca.danielvega.learning.seleniumforcrudsimple.specialTests.display;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SpecialTests;
import ca.danielvega.learning.seleniumforcrudsimple.pages.DisplayDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DisplayTest extends CrudSimpleTest {

    @Test()
    @Category({FastTests.class, SpecialTests.class})
    @Explanation(
            title = "Display page can remove a device.",
            description = "From the Display, try to remove a specific device.",
            steps = {
                "Create a device",
                "Go to the Display page of the newly device. (you can use the search in the grid page)",
                "From Display page, remove the newly device.",
                "Assert = The device must be removed and it is not longer in the Grid page."
            }
    )
    public void canRemoveTheCurrentDevice() {
        DeviceCreator.createDevice();
        DisplayDevicePage.goTo(DeviceCreator.getPreviousName());
        DisplayDevicePage.removeDevice();
        assertFalse("Cannot remove the current device.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));
    }

}
