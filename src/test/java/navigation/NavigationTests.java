package navigation;

import base.BaseTests;
import org.testng.annotations.Test;

public class NavigationTests extends BaseTests {

    @Test
    public void navigationTest(){
        homePage.clickShopNow();
        getWindowManager().goBack();
    }
}
