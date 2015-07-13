package ca.danielvega.learning.seleniumforcrudsimple.selenium;

import ca.danielvega.learning.seleniumforcrudsimple.workflows.DeviceCreator;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    public static WebDriver instance;

    public static void initilize() {
        cleanOtherProcess();
        setFirefox();
//        setChrome();
//        setInternetExplorer();
        setCookies();
   //     instance.manage().window().maximize();

        DeviceCreator.initilize();
        turnOnWait();
    }

    private static void setFirefox() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);

        instance = new FirefoxDriver(profile);
    }

    private static void setChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\daniel\\programa\\selenium\\chromedriver.exe");
        instance = new ChromeDriver();
    }

    private static void setInternetExplorer() {
        System.setProperty("webdriver.ie.driver", "C:\\Users\\daniel\\programa\\selenium\\IEDriverServer.exe");
//        System.setProperty("webdriver.ie.driver", "C:\\Users\\daniel\\programa\\selenium\\ieDrivers32bits\\IEDriverServer.exe");
        instance = new InternetExplorerDriver();
//        File ffpath = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//        FirefoxBinary ffbinary = new FirefoxBinary(ffpath);
//        FirefoxProfile ffprofile = new FirefoxProfile();
//        instance = new FirefoxDriver(ffbinary, ffprofile );
    }

    public static void cleanup() {
        DeviceCreator.cleanup();
        if(isAlertPresent()){
            Driver.instance.switchTo().alert().dismiss();
        }
        instance.close();
        instance.quit();
    }

    public static void waitSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<WebElement> findElementsWithoutWait(WebElement webElement, By by) {
        List<WebElement> elements;
        turnOffWait();
        try {
            elements = webElement.findElements(by);
        } finally {
            turnOnWait();
        }

        return elements;
    }

    private static void turnOffWait() {
        instance.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    private static void turnOnWait() {
        instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static boolean isAlertPresent() {
        try {
            Alert alert = Driver.instance.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    private static void cleanOtherProcess() {
//        WindowsUtils.tryToKillByName("notepad++.exe");
    }

    private static void setCookies() {
        instance.manage().deleteAllCookies();
        //esto lo puedes usar en el framawork para leer las cookies creadas
        //por defecto el browser tendra 0
//        Set<Cookie> cokkiesSet = instance.manage().getCookies();
//        System.out.println("Cookies count: " + cokkiesSet.size());
    }

    public static File takesScreenshot() {
        File shFile = ((TakesScreenshot) instance).getScreenshotAs(OutputType.FILE);
        File filed = new File("./target/temp/screenshot" + (new Date().getTime()) + ".png");
        try {
            FileUtils.copyFile(shFile, filed);
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filed;
    }

    public static File takesAlertScreenshot() {

        String imageName = "screenshot" + (new Date().getTime()) + ".png";
        String imagePath = "./target/temp/" + imageName;
        File filed = new File(imagePath);

        BufferedImage image;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", filed);
        } catch (AWTException | IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filed;
    }
 

}
