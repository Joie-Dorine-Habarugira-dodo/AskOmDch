package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By shopNow= By.cssSelector("#post-61 > div > div.wp-block-cover.alignfull.has-background-dim > div > div > div > div > div:nth-child(1) > a");
    private By cart= By.cssSelector(".ast-cart-menu-wrap");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public StorePage clickShopNow(){
        driver.findElement(shopNow).click();
        return new StorePage(driver);
    }
    public CartPage clickCart(){
        driver.findElement(cart).click();
        return new CartPage(driver);
    }

    public void clickLink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }
}
