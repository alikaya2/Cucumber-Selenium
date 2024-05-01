package pages;

import io.cucumber.java.en.Given;
import stepDefinitions.Steps;

import java.net.MalformedURLException;

public class SignInPageSteps {

    public SignInPageSteps() throws MalformedURLException {
    }
    Steps steps = new Steps();

    @Given("Login with valid users")
    public void signInLoginWithUser() {
        steps.loginWithUser();
    }
}
