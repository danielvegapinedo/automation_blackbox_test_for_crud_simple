package ca.danielvega.learning.seleniumforcrudsimple.pages;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Step;
import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author daniel
 */
public class LoginPage extends BasePage{

    @ru.yandex.qatools.allure.annotations.Step("Go to Login page.")
    @Step(description="Go to Login page.")
    public static void goTo() {

        Driver.instance.navigate().to(Address.LOGING.getURL());
        WebDriverWait wait = new WebDriverWait(Driver.instance, 20);
        wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                WebElement c = d.switchTo().activeElement();
                boolean equals = c.getAttribute("id").equals("email");
                return c;
            }
        });
    }

   
    public static LoginCommand loginAs(String userName) {
        return new LoginCommand(userName);
    }

    public static class LoginCommand {

        private final String userName;
        private String password;

        public LoginCommand(String userName) {
            this.userName = userName;
        }

        public LoginCommand withPassword(String password) {
            this.password = password;
            return this;
        }

        
        public void login() {
            WebElement loginInput = Driver.instance.findElement(By.id("email"));
            loginInput.sendKeys(userName);

            WebElement passwordInput = Driver.instance.findElement(By.id("password"));
            passwordInput.sendKeys(password);

            WebElement submitButton = Driver.instance.findElement(By.id("submit"));
            submitButton.click();
        }
    }

}
