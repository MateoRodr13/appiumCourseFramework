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
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.fillForm(input.get("country"), input.get("name"), input.get("gender"));
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
