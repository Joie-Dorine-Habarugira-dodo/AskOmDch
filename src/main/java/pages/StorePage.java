package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class StorePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By products= By.cssSelector("li.product");
    private By addToCartBtn = By.cssSelector("a.add_to_cart_button");
    private By viewCartLink= By.cssSelector("a.added_to_cart.wc-forward");

    private By catDropdown= By.cssSelector("span.select2");

    private By searchBar= By.id("woocommerce-product-search-field-0");
    private By pagetitle= By.cssSelector("h1.page-title");
    private By productTitles= By.cssSelector("h2.woocommerce-loop-product__title");

    private By sliderHandles= By.cssSelector(".ui-slider-handle");
    private By minPriceLabel= By.cssSelector(".from");
    private By maxPriceLabel= By.cssSelector(".to");
    private By filterBtn= By.cssSelector(".price_slider_amount button");
    private By productPrice= By.cssSelector("woocommerce-Price-amount");;

    public StorePage(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickAddToCartBtn(int index){
        List<WebElement> productList= driver.findElements(products);
        productList.get(index).findElement(addToCartBtn).click();
    }

    public boolean isViewCartLinkVisible(int index){
        WebElement product= driver.findElements(products).get(index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement viewCart= wait.until(visibilityOfElementLocated(By.cssSelector("a.added_to_cart.wc-forward")));
       return viewCart.isDisplayed();
    }

    public void selectCategory(String categoryName){
        driver.findElement(catDropdown).click();
        List<WebElement> categories = driver.findElements(By.cssSelector("li.select2-results__option"));
        for(WebElement c: categories){
            wait.until(ExpectedConditions.elementToBeClickable(c));
            String normalizedText= c.getText().replaceAll("\\(.*?\\)", "").replace("’", "'").trim().toLowerCase();
            if(normalizedText.equalsIgnoreCase(categoryName)){
                c.click();
                return;
            }
        }
    }

    public boolean isCategoryPageOpened(String categoryName){
        String title= wait.until(ExpectedConditions.visibilityOfElementLocated(pagetitle)).getText();
        return title.equalsIgnoreCase(categoryName);
    }

    public boolean searchProduct(String productName){
        driver.findElement(searchBar).click();
        driver.findElement(searchBar).sendKeys(productName + Keys.ENTER);

        String pageTitle=driver.findElement(pagetitle).getText().toLowerCase();
        return pageTitle.contains(productName);
    }
    public boolean productsMatchSearch(String productName){
        List<WebElement> products= driver.findElements(productTitles);
        if(products.isEmpty()){
            return false;
        }
        for(WebElement product: products){
            String productTitle= product.getText().toLowerCase();
            if (!productTitle.contains(productName.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public void filterPrice(int min, int max){
        List<WebElement> handles= driver.findElements(sliderHandles);
        WebElement minHandle= handles.get(0);
        WebElement maxHandle= handles.get(1);

        minHandle.click();
        for(int i=10; i<min; i+=10){
            minHandle.sendKeys(Keys.ARROW_RIGHT);
        }

        maxHandle.click();
        for(int i=150; i>max; i-=10){
            maxHandle.sendKeys(Keys.ARROW_LEFT);
        }
        driver.findElement(filterBtn).click();
    }

    public boolean isPriceRangeUpdated(String min, String max){
        String minPrice= driver.findElement(minPriceLabel).getText();
        String maxPrice= driver.findElement(maxPriceLabel).getText();

        return minPrice.contains(min) && maxPrice.contains(max);
    }

    public boolean productsInPriceRange(int min, int max){
        List<WebElement> prices= driver.findElements(productPrice);
        for(WebElement price: prices){
            if(Integer.valueOf(price.getText())<min || Integer.valueOf(price.getText())>max){
                return false;
            }
        }
        return true;
    }
}


