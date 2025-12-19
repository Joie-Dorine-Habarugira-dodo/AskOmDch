package shop;

import base.BaseTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class PriceFilterTests extends BaseTests {
    @Test
    public void filterPriceTest(){
        var storePage= homePage.clickShopNow();
        storePage.filterPrice(50,80);
        assertTrue(storePage.isPriceRangeUpdated("50","80"),"assert1 failed");
        assertTrue(storePage.productsInPriceRange(50,80), "assert2 failed");
    }
}
