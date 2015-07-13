package ca.danielvega.learning.seleniumforcrudsimple.crudLinkTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.CrudLinkTests;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.FastTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.Address;
import ca.danielvega.learning.seleniumforcrudsimple.pages.DisplayDevicePage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

public class DisplayLinksTest extends CrudSimpleTest {

    @Test
    @Category({FastTests.class, CrudLinkTests.class})
    @Explanation(
            title = "Can Display page link CRUD pages.",
            description = "Test the GRID, UPDATE and CREATE links in the Display page.",
            steps = {
                "Go to Display page.",
                "Click on Grid icon of top menu.",
                "Assert = Browser must show Grid page."
                    
            }
    )
    public void canLinkFromDisplayToGrid() {
        assertTrue("Create page cannot link to Grid, Update and Create pages.", DisplayDevicePage.isLinkValid(Address.GRID, Address.UPDATE, Address.CREATE));
    }

}
