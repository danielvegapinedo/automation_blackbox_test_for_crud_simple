package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class DisplayDevicePage extends BasePage {

    @Step("Go to Display page of the device {0}")
    public static void goTo(String deviceName) {
        Driver.instance.navigate().to(Address.DISPLAY.getURL() + "?name=" + deviceName);
    }

    public static String getDeviceName() {
        WebElement name = Driver.instance.findElement(By.id("name"));
        if (name != null) {
            return name.getAttribute("value");
        }

        return "";
    }

    public static DisplayDeviceCommand removeDevice() {
        return new DisplayDeviceCommand().removeDevice();
    }

    @Step("Verify if is at Display page.")
    public static boolean isAt() {
        //refactor: generic way
        return Driver.instance.findElement(By.id("pageTitle")).getText().equalsIgnoreCase("Display Device");
    }

    @Step("Display page links are valids {0}")
    public static boolean isLinkValid(Address... linkList) {
        int length = linkList.length;
        int found = 0;
        WebElement aElement;
        String deviceName = GridDevicePage.getFirstDeviceName();

        for (Address link : linkList) {
            goTo(deviceName);
            switch (link) {
                case GRID:
                    aElement = Driver.instance.findElement(By.id("linkGrid"));
                    aElement.click();
                    if (GridDevicePage.isAt()) {
                        found++;
                    }
                    break;
                case CREATE:
                    aElement = Driver.instance.findElement(By.id("linkCreate"));
                    aElement.click();
                    if (CreateDevicePage.isAt()) {
                        found++;
                    }
                    break;
                case UPDATE:
                    aElement = Driver.instance.findElement(By.id("linkUpdate"));
                    aElement.click();
                    if (UpdateDevicePage.isAt()) {
                        found++;
                    }
                    break;
                default:
                    //do nothing
                    break;
            }

        }
        goTo(deviceName);
        return found == length;
    }

    public static class DisplayDeviceCommand {

        @Step("Remove the device doing click on the remove button.")
        public DisplayDeviceCommand removeDevice() {
            WebElement aElement = Driver.instance.findElement(By.id("linkRemove"));
            aElement.click();
            return this;
        }

    }

    public static class Navegation {

        public static class Update {

            @Step("Click on the Update link.")
            public static void select() {
                Driver.instance.findElement(By.id("linkUpdate")).click();
            }

        }

        public static class Grid {

            @Step("Click on the Grid link.")
            public static void select() {
                Driver.instance.findElement(By.id("linkGrid")).click();
            }

        }

        public static class Create {

            @Step("Click on the create link.")
            public static void select() {
                Driver.instance.findElement(By.id("linkCreate")).click();
            }
        }

    }
}
