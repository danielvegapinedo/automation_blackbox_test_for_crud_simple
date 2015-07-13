package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import ca.danielvega.learning.seleniumforcrudsimple.navigation.TopNavigation;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

public class UpdateDevicePage extends BasePage{

    @Step("Go to Update page of the device {0}")
    public static void goTo(String deviceName) {
        Driver.instance.navigate().to(Address.UPDATE.getURL() + "?name=" + deviceName);
    }

    @Step("From Top navigation of Update page go to Create page.")
    public static void goFromTopNavigationTo() {
        TopNavigation.Create.select();
    }

    public static String getDeviceName() {
        WebElement name = Driver.instance.findElement(By.id("name"));
        if (name != null) {
            return name.getAttribute("value");
        }

        return "";
    }

    @Step("Verify if is at Update page.")
    public static boolean isAt() {
        //refactor: generic way
        return Driver.instance.findElement(By.id("pageTitle")).getText().equalsIgnoreCase("Update Device");
    }

    
    public static UpdateDeviceCommand updateDevice() {
        return new UpdateDeviceCommand();
    }

    
    public static Match createMatch() {
        return new Match();
    }

    @Step("Update page links are valids {0}")
    public static boolean isLinkValid(Address... linkList) {
        int length = linkList.length;
        int found = 0;
        WebElement aElement;
        GridDevicePage.goTo();
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
                case DISPLAY:
                    aElement = Driver.instance.findElement(By.id("linkDisplay"));
                    aElement.click();
                    if (DisplayDevicePage.isAt()) {
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

    public static class UpdateDeviceCommand {

        private String status;
        private String mode;
        private String type;
        private String tariff;
        private String customer;

        @Step("Use the device status = {0}.")
        public UpdateDeviceCommand withStatus(String status) {
            this.status = status;
            return this;
        }

        @Step("Use the device mode = {0}.")
        public UpdateDeviceCommand withMode(String mode) {
            this.mode = mode;
            return this;
        }

        @Step("Use the device type = {0}.")
        public UpdateDeviceCommand withType(String type) {
            this.type = type;
            return this;
        }

        @Step("Use the device tariff = {0}.")
        public UpdateDeviceCommand withTariff(String tariff) {
            this.tariff = tariff;
            return this;
        }

        @Step("Use the device customer = {0}.")
        public UpdateDeviceCommand withCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public void update() {
//            WebElement nameInput = Driver.instance.findElement(By.id("name"));
//            nameInput.sendKeys(name);
            WebElement statusInput = Driver.instance.findElement(By.id("status"));
            statusInput.clear();
            statusInput.sendKeys(status);
            WebElement modeInput = Driver.instance.findElement(By.id("mode"));
            modeInput.clear();
            modeInput.sendKeys(mode);
            WebElement typeInput = Driver.instance.findElement(By.id("type"));
            typeInput.clear();
            typeInput.sendKeys(type);
            WebElement tariffInput = Driver.instance.findElement(By.id("tariff"));
            tariffInput.clear();
            tariffInput.sendKeys(tariff);
            WebElement customerInput = Driver.instance.findElement(By.id("customer"));
            customerInput.clear();
            customerInput.sendKeys(customer);

            WebElement submitButton = Driver.instance.findElement(By.id("submit"));
            submitButton.click();
        }

    }

    public static class Match {

        private String name;
        private String status;
        private String mode;
        private String type;
        private String tariff;
        private String customer;
        public Map<String, Boolean> matchMap;

        public Match() {
            this.matchMap = new HashMap<>();
            matchMap.put("name", false);
            matchMap.put("status", false);
            matchMap.put("mode", false);
            matchMap.put("type", false);
            matchMap.put("tariff", false);
            matchMap.put("customer", false);
        }

        public Match withName(String name) {
            matchMap.put("name", true);
            this.name = name;
            return this;
        }

        public Match withStatus(String status) {
            matchMap.put("status", true);
            this.status = status;
            return this;
        }

        public Match withMode(String mode) {
            matchMap.put("mode", true);
            this.mode = mode;
            return this;
        }

        public Match withType(String type) {
            matchMap.put("type", true);
            this.type = type;
            return this;
        }

        public Match withTariff(String tariff) {
            matchMap.put("tariff", true);
            this.tariff = tariff;
            return this;
        }

        public Match withCustomer(String customer) {
            matchMap.put("customer", true);
            this.customer = customer;
            return this;
        }

        @Step("Verify if field values match.")
        public boolean match() {
            for (Entry<String, Boolean> entry : matchMap.entrySet()) {
                String field = entry.getKey();
                boolean toMatch = entry.getValue();
                switch (field) {
                    case "name":
                        if (toMatch && !name.equals(Field.getInputValue("name"))) {
                            return false;
                        }
                        break;
                    case "status":
                        if (toMatch && !status.equals(Field.getInputValue("status"))) {
                            return false;
                        }
                        break;
                    case "mode":
                        if (toMatch && !mode.equals(Field.getInputValue("mode"))) {
                            return false;
                        }
                        break;
                    case "type":
                        if (toMatch && !type.equals(Field.getInputValue("type"))) {
                            return false;
                        }
                        break;
                    case "tariff":
                        if (toMatch && !tariff.equals(Field.getInputValue("tariff"))) {
                            return false;
                        }
                        break;
                    case "customer":
                        if (toMatch && !customer.equals(Field.getInputValue("customer"))) {
                            return false;
                        }
                        break;
                    default:
                        //do nothing
                        break;
                }
            }
            return true;
        }

    }

    protected static class Field {

        public static String getInputValue(String id) {
            return Driver.instance.findElement(By.id(id)).getAttribute("value");
        }
    }
}
