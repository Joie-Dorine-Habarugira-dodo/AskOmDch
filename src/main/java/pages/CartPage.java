package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By removeBtn= By.cssSelector(".remove");


    public CartPage(WebDriver driver){
        this.driver = driver;
    }
}
