package pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import stepDefinitions.Steps;

import java.net.MalformedURLException;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class HomePageSteps{

    public HomePageSteps() throws MalformedURLException {
    }
    Steps steps = new Steps();

    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Then("Check : Access to website")
    public void homePagecheckSuccessfulLogin () {
        steps.checkSuccessfulLogin();
    }

    @When("Click Home Page Sign In Button")
    public void clickLoginButtonFromHomePage() {
        steps.clickSignInElement();

    }
    @When ("^Fill in : Main Page : Enter \"([^\"]*)\" Searchbox$")
    public void mainPageSearchBoxSendKeys(String parameter) {
        steps.sendKeysMainPageSearchBox(parameter);

    }


}