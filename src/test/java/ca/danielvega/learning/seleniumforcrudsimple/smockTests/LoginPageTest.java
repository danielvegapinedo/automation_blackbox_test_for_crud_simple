package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;
import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest;
import ca.danielvega.learning.seleniumforcrudsimple.pages.GridDevicePage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;


public class LoginPageTest  extends CrudSimpleTest{
    @Test
    @Category(SmockTests.class)
    @Explanation(
            title = "Login.",
            description = "Can login in the application",
            steps = {
                "Go to Login page.",
                "Try to login as Admin",
                "Assert = Login must be accepted and the browser must show the Grid page."
                    
            }
    )

    public void adminUserCanLogin() {
        assertTrue("Failed to login", GridDevicePage.isAt());
    }

}
