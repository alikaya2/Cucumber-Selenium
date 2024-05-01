package pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import stepDefinitions.Steps;

import java.net.MalformedURLException;

public class ProductPageSteps {

    public ProductPageSteps() throws MalformedURLException {
    }
    Steps steps = new Steps();

    @And("^Find : Product Page : First product which one \"([^\"]*)\" dolar$")
    public void findProductHigherThanNumber(String price) {
        steps.findProductFromList(price);

    }

    @Then("Verify : Product Page : Check this product")
    public void checkProduct() {
        steps.checkFirstProduct();
    }

}
