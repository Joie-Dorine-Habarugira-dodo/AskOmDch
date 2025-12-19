package shop;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StoreTests extends BaseTests {
    @Test
    public void StoreTest(){
        homePage.clickShopNow();

    }

    @Test
    public void AddToCartTest(){
        var storePage= homePage.clickShopNow();
        storePage.clickAddToCartBtn(4);
        assertTrue(storePage.isViewCartLinkVisible(4));
    }

    @Test
    public void SearchProductTest(){
        var storePage= homePage.clickShopNow();
        Assert.assertTrue(storePage.searchProduct("jeans"));
    }
}
