package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import ca.danielvega.learning.seleniumforcrudsimple.navigation.TopNavigation;
import static ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver.findElementsWithoutWait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *
 * @author daniel
 */
public class GridDevicePage extends BasePage{

    private static int lastCount;

    @Step("Go to Grid page.")
    public static void goTo() {
        Driver.instance.navigate().to(Address.GRID.getURL());
    }

    @Step("From top navigation fo to Create page = {0}")
    public static void goFromTopNavigationTo(Address address) {
        switch (address) {
            case CREATE:
                Driver.instance.findElement(By.id("linkCreate")).click();
                break;

            default:
                //do nothing
                break;
        }
    }

    @Step("Search device using the string = {0}")
    public static void searchDevice(String searchString) {
        if(!isAt()){
            GridDevicePage.goTo();
        }
        WebElement inputSearch = Driver.instance.findElement(By.id("inputSearch"));
        WebElement btnSearch = Driver.instance.findElement(By.id("btnSearch"));
        //WebElement btnCleanSearch = Driver.instance.findElement(By.id("btnCleanSearch"));
        
        inputSearch.clear();
        inputSearch.sendKeys(searchString);
        btnSearch.click();
    }
    
    @Step("Clean the serach input field.")
    public static void cleanSearch(){
        WebElement btnCleanSearch = Driver.instance.findElement(By.id("btnCleanSearch"));
        btnCleanSearch.click();
    }

    @Step("Verify if is at Grid page.")
    public static boolean isAt() {
//refactor: generic way
        return Driver.instance.findElement(By.id("pageTitle")).getText().equalsIgnoreCase("Device Grid");
    }

    @Step("Use the fisrt device name.")
    public static String getFirstDeviceName() {
        WebElement tableGrid = Driver.instance.findElement(By.id("tableGrid"));
        WebElement tBody = tableGrid.findElement(By.tagName("tbody"));
        List<WebElement> trList = tBody.findElements(By.tagName("tr"));
        WebElement td = trList.get(0).findElements(By.tagName("td")).get(0);
        return td.getText();
    }

        @Step("Remove the fisrt device name.")
    public static String removeFirstDevice() {
        String deviceName = getFirstDeviceName();
        WebElement tableGrid = Driver.instance.findElement(By.id("tableGrid"));
        WebElement tBody = tableGrid.findElement(By.tagName("tbody"));
        List<WebElement> trList = tBody.findElements(By.tagName("tr"));
        WebElement td = trList.get(0).findElement(By.id("linkRemove" + deviceName));
        td.click();
        return deviceName;
    }

    /**
     * Search a device in the grid and remove it.
     * If the device doen not exist, the method return false.
     * @param deviceName
     * @return 
     */
    @Step("Remove the device \"{0}\".")
    public static boolean removeDevice(String deviceName) {
        if (!doesDeviceExistWithName(deviceName)) {
            return false;
        }

        WebElement tableGrid = Driver.instance.findElement(By.id("tableGrid"));
        WebElement tBody = tableGrid.findElement(By.tagName("tbody"));
        List<WebElement> trList = tBody.findElements(By.tagName("tr"));

        for (WebElement tr : trList) {
            List<WebElement> linkList = findElementsWithoutWait(tr, By.id("linkRemove" + deviceName));
            if (!linkList.isEmpty()) {
                linkList.get(0).click();
                return true;
            }
        }

        return false;
    }

    /**
     * This method is only for test
     *
     * @param deviceName
     * @return
     */
    @Step("Remove the device \"{0}\".")
    public static boolean removeDeviceUsingActions(String deviceName) {
        if (!doesDeviceExistWithName(deviceName)) {
            return false;
        }

        WebElement tableGrid = Driver.instance.findElement(By.id("tableGrid"));
        WebElement tBody = tableGrid.findElement(By.tagName("tbody"));
        List<WebElement> trList = tBody.findElements(By.tagName("tr"));

        for (WebElement tr : trList) {
            List<WebElement> aList = findElementsWithoutWait(tr, By.linkText(deviceName));

            if (!aList.isEmpty()) {
                Actions action = new Actions(Driver.instance)
                        .moveToElement(findElementsWithoutWait(tr, By.className("imgDelete")).get(0))
                        .click();
                action.perform();
                return true;
            }
        }

        return false;
    }

    @Step("Go to previous page.")
    public static boolean prevPage() {
        try {
            WebElement paginationPrev = Driver.instance.findElement(By.id("paginationPrev"));
            paginationPrev.click();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    @Step("Go to next page.")
    public static boolean nextPage() {
        try {
            WebElement paginationNext = Driver.instance.findElement(By.id("paginationNext"));
            paginationNext.click();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Step("Grid page links are valids {0}")
    public static boolean isLinkValid(Address... linkList) {
        String deviceName = GridDevicePage.getFirstDeviceName();
        WebElement aElement;

        int length = linkList.length;
        int found = 0;

        for (Address link : linkList) {
            goTo();
            switch (link) {
                case UPDATE:
                    aElement = Driver.instance.findElement(By.id("linkUpdate" + deviceName));
                    aElement.click();
                    if (UpdateDevicePage.isAt()) {
                        found++;
                    }
                    TopNavigation.Grid.select();
                    break;
                case CREATE:
                    aElement = Driver.instance.findElement(By.id("linkCreate"));
                    aElement.click();
                    if (CreateDevicePage.isAt()) {
                        found++;
                    }
                    TopNavigation.Grid.select();
                    break;
                case DISPLAY:
                    aElement = Driver.instance.findElement(By.id("linkDisplay" + deviceName));
                    aElement.click();
                    if (DisplayDevicePage.isAt()) {
                        found++;
                    }
                    TopNavigation.Grid.select();
                    break;
                default:
                    //do nothing
                    break;
            }
        }
        goTo();
        return found == length;
    }

    @Step("Store device count.")
    public static void storeDeviceCount() {
        lastCount = getDeviceCount();
    }

    @Step("Check stored device count.")
    public static int previousStoredDevices() {
        return lastCount;
    }

    @Step("Check current device count.")
    public static int currentStoredDevices() {
        return getDeviceCount();
    }

    @Step("Check device count.")
    private static int getDeviceCount() {
        return Integer.parseInt(Driver.instance.findElement(By.id("totalItems")).getText().split(":")[1].trim());

    }

    @Step("Check if device {0} exists.")
    public static boolean doesDeviceExistWithName(String deviceName) {
        int totalPage = Integer.parseInt(Driver.instance.findElement(By.id("totalPage")).getText());
        GridDeviceCommand gridDeviceCommand = new GridDeviceCommand();
        for (int i = 1; i <= totalPage; i++) {
            gridDeviceCommand.jumpToPage(i);
            if (doesDeviceExistInCurrentPageWithName(deviceName)) {
                return true;
            }
        }
        return false;
    }

    @Step("Check if device {0} exists in the current page.")
    public static boolean doesDeviceExistInCurrentPageWithName(String deviceName) {
        WebElement tableGrid = Driver.instance.findElement(By.id("tableGrid"));
        WebElement tBody = tableGrid.findElement(By.tagName("tbody"));
        List<WebElement> trList = tBody.findElements(By.tagName("tr"));

        for (WebElement tr : trList) {
            WebElement td = tr.findElements(By.tagName("td")).get(0);
            if (td.getText().equals(deviceName)) {
                return true;
            }
        }

        return false;
    }

    public static class GridDeviceCommand {

        private int pageNumber = -1;

        @Step("Junp to page {0}")
        public GridDeviceCommand jumpToPage(int pageNumber) {
            int currentPage = Integer.parseInt(Driver.instance.findElement(By.id("currentPage")).getText());
            int totalPage = Integer.parseInt(Driver.instance.findElement(By.id("totalPage")).getText());
            if (pageNumber <= 0 || pageNumber > totalPage) {
                throw new IllegalStateException("pageNumber to jump is out of range: " + pageNumber);
            }
            if (pageNumber == currentPage) {
                this.pageNumber = pageNumber;
                return this;
            }

            this.pageNumber = pageNumber;
            Driver.instance.navigate().to(Address.GRID.getURL() + "?pg=" + pageNumber);

            return this;
        }

        @Step("Remove device.")
        public void removeDevice() {
            WebElement aElement = Driver.instance.findElement(By.id("linkRemove"));
            aElement.click();

        }

    }

    public static class Navegation {

        public static class Display {

            @Step("Click in Diplay button of the {0} device.")
            public static void select(String deviceName) {
                Driver.instance.findElement(By.id("linkDisplay" + deviceName)).click();
            }

        }

        public static class Update {

            @Step("Click in Update button of the {0} device.")
            public static void select(String deviceName) {
                Driver.instance.findElement(By.id("linkUpdate" + deviceName)).click();
            }

        }

    }
}
