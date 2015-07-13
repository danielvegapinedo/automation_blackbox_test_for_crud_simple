package ca.danielvega.learning.seleniumforcrudsimple.crudLinkTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.CrudLinkTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.Address;
import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;


public class CreateLinksTest extends CrudSimpleTest {

    @Test
    @Category({FastTests.class, CrudLinkTests.class})
    @Explanation(
            title = "Can Create page link Grid page.",
            description = "Test the GRID link in the Display page.",
            steps = {
                "Go to Create page.",
                "Click on Grid icon of top menu.",
                "Assert = Browser must show Grid page."
                    
            }
    )
    public void canLinkFromCreateToGrid() {
        assertTrue("Create page cannot link to Grid page.", CreateDevicePage.isLinkValid(Address.GRID));
        report.log(LogStatus.PASS, "Create page can link to Grid page.");
        
    }
}
