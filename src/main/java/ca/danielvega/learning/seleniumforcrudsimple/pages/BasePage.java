package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

public abstract class BasePage {

    private static boolean isAlertErrorPresentAndClose(boolean close) {
        try {
            Alert alert = Driver.instance.switchTo().alert();
            if (close) {
                alert.accept();
            }
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static boolean isAlertErrorPresent() {
        return isAlertErrorPresentAndClose(false);
    }

    public static boolean closeAlert() {
        return isAlertErrorPresentAndClose(true);
    }

    public static String getAlertErrorMessage() {
        Alert alert = Driver.instance.switchTo().alert();
        return alert.getText();
    }

    @Attachment(type = "image/png")
    public static byte[] makeScreenshot() {
        return ((TakesScreenshot) Driver.instance).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public static byte[] attachVideo() {
        return ((TakesScreenshot) Driver.instance).getScreenshotAs(OutputType.BYTES);
    }

}
