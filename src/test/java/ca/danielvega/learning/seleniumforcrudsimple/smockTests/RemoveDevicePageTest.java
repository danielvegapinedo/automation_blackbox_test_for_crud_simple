package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Recorder;
import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.RemoveDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.experimental.categories.Category;

public class RemoveDevicePageTest extends CrudSimpleTest {

    @Before
    public void initialize() {
        DeviceCreator.createDevice();
    }

    @Test
    @Category(SmockTests.class)
    @Explanation(
            title = "Can remove using Remove page URL.",
            description = "Can do remove using only the url.",
            steps = {
                "Using browser, go to  http://localhost:8080/crud_simple/remove_device.jsp?name=SOME_DEVICE.",
                "Go to the Grid page.",
                "Assert = The Grid page has not contains the device."

            }
    )
    @Recorder()
    public void removeDevice() {
        report.log(LogStatus.INFO, "Device Name", DeviceCreator.getPreviousName());
        RemoveDevicePage.remove(DeviceCreator.getPreviousName());
        assertFalse("Device was not removed.", GridDevicePage.doesDeviceExistWithName(DeviceCreator.getPreviousName()));

    }

}
