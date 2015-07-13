package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *
 * @author daniel
 */
public class RemoveDevicePage extends BasePage{

    @Step("Remove {0} device using URL.")
    public static void remove(String deviceName) {
        //refactor: general menu navigation
        Driver.instance.navigate().to(Address.REMOVE.getURL() + "?name=" + deviceName);
    }

}
