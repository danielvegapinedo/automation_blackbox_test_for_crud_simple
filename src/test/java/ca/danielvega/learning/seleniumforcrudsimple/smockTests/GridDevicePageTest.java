package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;

public class GridDevicePageTest extends CrudSimpleTest {

    @Test
    @Category(SmockTests.class)
    @Explanation(
            title = "Pagination.",
            description = "Grid can go to next or prev page.",
            steps = {
                "Go to grid.",
                "Click on next button",
                "Assert = Grid must show the next page.",
                "Click on prev button",
                "Assert = Grid must show the previous page."
                    
            }
    )
    public void goNextPrev() {

        assertTrue("Nextpage failed", GridDevicePage.nextPage());
        assertTrue("Prevpage failed", GridDevicePage.prevPage());

    }

}
