package shop;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SelectCategoryTests extends BaseTests {
    @Test
    public void SelectCategoryTest(){
        var storePage= homePage.clickShopNow();
        String category= "Men's shirts";
        storePage.selectCategory(category);
        assertTrue(storePage.isCategoryPageOpened(category));
    }
}
