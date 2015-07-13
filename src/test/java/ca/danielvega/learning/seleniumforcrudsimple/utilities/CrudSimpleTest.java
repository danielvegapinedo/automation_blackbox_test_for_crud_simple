package ca.danielvega.learning.seleniumforcrudsimple.utilities;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.Report;
import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import ca.danielvega.learning.seleniumforcrudsimple.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.yandex.qatools.allure.annotations.Step;

abstract public class CrudSimpleTest extends BaseTest {
    protected static ExtentReports extent;

    @BeforeClass
    @Step("Login as admin.")
    public static void setUpClass() {
        System.out.println("CrudSimpleTest BeforeClass");
        Report.initilize("./target/extends_report.html", true, true);
        extent = Report.getInstance();
        
        Driver.initilize();
        LoginPage.goTo();
        LoginPage.loginAs("admin").withPassword("admin").login();
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("CrudSimpleTest AfterClass");
        Driver.cleanup();
        Report.cleanup();

    }

}
