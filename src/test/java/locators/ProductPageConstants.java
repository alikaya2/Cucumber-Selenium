package locators;

import org.openqa.selenium.By;

public class ProductPageConstants {

    private ProductPageConstants(){

    }

    public static final By PRODUCTPAGE_SEARCH_RESULT = By.cssSelector("[data-ui-id='page-title-wrapper']");
    public static final By PRODUCT_PAGE_NUMBER  = By.cssSelector(".toolbar-products:nth-child(4) ul");
    public static final By LIST_DROOPDOWN = By.xpath("//select[@id='limiter'])[2]");
    public static final By PRODUCT_PRICE = By.cssSelector("[class='price']");
    public static final By PRODUCT_ADD_TO_CART = By.cssSelector("[title='Add to Cart']");
    public static final By PRODUCT_PAGE_NEXT_BUTTON = By.cssSelector(".toolbar-products:nth-child(4) [title='Next']");






}
