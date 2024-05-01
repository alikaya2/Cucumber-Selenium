package pages;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.Steps;

import java.net.MalformedURLException;


public class HomePageSteps{

    public HomePageSteps() throws MalformedURLException {
    }
    Steps steps = new Steps();

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