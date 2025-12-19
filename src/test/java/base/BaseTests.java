package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.WindowsManager;

public class BaseTests {
    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
public void setup(){
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\AskOmDch\\resources\\chromedriver.exe");
    driver= new ChromeDriver();
    goHome();
    homePage= new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://askomdch.com/");
    }

//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }
    public WindowsManager getWindowManager(){
        return new WindowsManager(driver);
    }
}