package ca.danielvega.learning.seleniumforcrudsimple.crudLinkTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.CrudLinkTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.Address;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

public class GridLinksTest extends CrudSimpleTest {

    @Test
    @Category({FastTests.class, CrudLinkTests.class})
    @Explanation(
            title = "Can Grid page link crud pages.",
            description = "Test the CRUD links in the Display page.",
            steps = {
                "Go to Grid page.",
                "Click on Display icon of grid table.",
                "Assert = Browser must show Display page.",
                "Click on Update icon of grid table.",
                "Assert = Browser must show Update page.",
                "Click on Create icon of grid table.",
                "Assert = Browser must show Create page.",}
    )
    public void canLinkFromGridToCrudPages() {

        assertTrue("Cannot link from Grid to crud pages", GridDevicePage.isLinkValid(Address.DISPLAY, Address.UPDATE, Address.CREATE));
    }

}
