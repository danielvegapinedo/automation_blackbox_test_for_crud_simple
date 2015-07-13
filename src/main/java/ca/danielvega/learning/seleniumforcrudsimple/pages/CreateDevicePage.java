package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class CreateDevicePage extends BasePage{

    @Step("Go to Create page.")
    public static void goTo() {
        Driver.instance.navigate().to(Address.CREATE.getURL());
    }

    public static CreateDeviceCommand createDevice() {
        return new CreateDeviceCommand();
    }

    @Step("Create page links are valids {0}")
    public static boolean isLinkValid(Address... linklist) {
        int length = linklist.length;
        int found = 0;

        for (Address links : linklist) {
            goTo();
            switch (links) {
                case GRID:
                    WebElement nameInput = Driver.instance.findElement(By.id("linkGrid"));
                    nameInput.click();
                    if (GridDevicePage.isAt()) {
                        found++;
                    }
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        goTo();
        return length == found;

    }

    @Step("Verify if is at Create page.")
    public static boolean isAt() {
        //refactor: generic way
        return Driver.instance.findElement(By.id("pageTitle")).getText().equalsIgnoreCase("Create Device");
    }

            
    public static class CreateDeviceCommand {

        private String name;
        private String status;
        private String mode;
        private String type;
        private String tariff;
        private String customer;

        @Step("Use the device name = {0}.")
        public CreateDeviceCommand withName(String name) {
            this.name = name;
            return this;
        }

        @Step("Use the device status = {0}.")
        public CreateDeviceCommand withStatus(String status) {
            this.status = status;
            return this;
        }

        @Step("Use the device mode = {0}.")
        public CreateDeviceCommand withMode(String mode) {
            this.mode = mode;
            return this;
        }

        @Step("Use the device type = {0}.")
        public CreateDeviceCommand withType(String type) {
            this.type = type;
            return this;
        }

        @Step("Use the device tariff = {0}.")
        public CreateDeviceCommand withTariff(String tariff) {
            this.tariff = tariff;
            return this;
        }

        @Step("Use the device customer = {0}.")
        public CreateDeviceCommand withCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        @Step("Create a device.")
        public void create() {
            WebElement nameInput = Driver.instance.findElement(By.id("name"));
            nameInput.sendKeys(name);
            WebElement statusInput = Driver.instance.findElement(By.id("status"));
            statusInput.sendKeys(status);
            WebElement modeInput = Driver.instance.findElement(By.id("mode"));
            modeInput.sendKeys(mode);
            WebElement typeInput = Driver.instance.findElement(By.id("type"));
            typeInput.sendKeys(type);
            WebElement tariffInput = Driver.instance.findElement(By.id("tariff"));
            tariffInput.sendKeys(tariff);
            WebElement customerInput = Driver.instance.findElement(By.id("customer"));
            customerInput.sendKeys(customer);

            WebElement submitButton = Driver.instance.findElement(By.id("submit"));
            submitButton.click();
        }

    }

}
