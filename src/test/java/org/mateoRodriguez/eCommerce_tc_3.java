package org.mateoRodriguez;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.mateoRodriguez.pageObjects.android.CartPage;
import org.mateoRodriguez.pageObjects.android.ProductCatalog;
import org.mateoRodriguez.testUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_3 extends AndroidBaseTest {

    /*@BeforeMethod(alwaysRun = true)
    public void preSetup(){
        formPage.setActivity();
    }
    @AfterMethod(alwaysRun = true)
    public void postSetup(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }*/

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\org\\mateoRodriguez\\testData\\eCommerce.json");
        //return new Object[][]{{"Mateo Rodriguez", "Male", "Colombia"}, {"Lina", "Female", "Argentina"}};
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.countrySelection(input.get("country"));
        Thread.sleep(1000);
        ProductCatalog productCatalog = formPage.pressShopButton();
        productCatalog.addItemToCartByIndex(0);
        productCatalog.addItemToCartByIndex(0);
        CartPage cartPage = productCatalog.openCart();
        Assert.assertEquals(cartPage.getTotalAmountDisplayed(), cartPage.getProductsSum());
        cartPage.viewTermAndConditions();
        cartPage.closeTermAndConditions();
        cartPage.pressEmailCheckBox();
        cartPage.pressBtnProceedShop();
        Thread.sleep(2000);

    }


}
