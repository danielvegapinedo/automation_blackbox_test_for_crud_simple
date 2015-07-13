package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.DisplayDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisplayDevicePageTest extends CrudSimpleTest {

    @Test()
    @Category(SmockTests.class)
    @Explanation(
            title = "Display page can remove the Device.",
            description = "Display page can remove the Device.",
            steps = {
                "Create a device",
                "Go to Display page of the newly device.",
                "Remove the device.",
                "Assert = After the creation, the device does not exist, ITry to search it using the Grid."
            }  
    )
    public void canRemoveOneDevice() {
        DeviceCreator.createDevice();
        DisplayDevicePage.goTo(DeviceCreator.getPreviousName());
        report.log(LogStatus.INFO, "Device Name", DeviceCreator.getPreviousName());
        DisplayDevicePage.removeDevice();
        assertFalse("Cannot remove the Device.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));
    }

}
