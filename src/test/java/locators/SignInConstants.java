package locators;

import org.openqa.selenium.By;

public class SignInConstants {

    private SignInConstants() {

    }

    public static final By LOGINEMAIL = By.id("email");
    public static final By LOGINPASSWORD = By.id("pass");
    public static final By SIGNINPAGESIGNINBUTTON = By.cssSelector(".action.login.primary");
}
