package locators;

import org.openqa.selenium.By;

public class HomePageConstants {

    private HomePageConstants() {

    }

    public static final By HOMEPAGEMAINIMG = By.cssSelector(".block-promo.home-main");
    public static final By HOMEPAGESIGNINBTN = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]");
    public static final By WELCOMETEXTELEMENT = By.cssSelector("div[class='panel header'] span[class='logged-in']");
    public static final By MAINPAGESEARCHBOX = By.cssSelector("#search");
    public static final By MAINPAGESEARCHBOXTANKSCOTTON = By.xpath("//*[contains(text(),'tanks cotton')]");

}
