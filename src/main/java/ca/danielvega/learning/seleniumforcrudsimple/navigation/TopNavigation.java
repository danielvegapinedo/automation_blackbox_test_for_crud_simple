package ca.danielvega.learning.seleniumforcrudsimple.navigation;

import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import org.openqa.selenium.By;

public class TopNavigation {

    public static class Grid {

        public static void select() {
            Driver.instance.findElement(By.id("linkGrid")).click();
        }
    }

    public static class Create {

        public static void select() {
            Driver.instance.findElement(By.id("linkCreate")).click();
        }

    }

    public static class Display {

        public static void select(String deviceName) {
            Driver.instance.findElement(By.id("linkDisplay")).click();
        }

    }

}
