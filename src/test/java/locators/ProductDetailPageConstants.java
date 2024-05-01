package locators;

import org.openqa.selenium.By;

public class ProductDetailPageConstants {
    private ProductDetailPageConstants (){

    }
    public static final By PRODCUT_DETAIL_ADD_TO_CART = By.cssSelector(" .action.primary.tocart");
    public static final By PRODUCT_DETAIL_PRODUCT_PRICE = By.cssSelector(".product-info-price .price-wrapper");


}
