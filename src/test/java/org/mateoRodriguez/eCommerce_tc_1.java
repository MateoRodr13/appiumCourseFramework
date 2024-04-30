package org.mateoRodriguez;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.mateoRodriguez.testUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_1 extends AndroidBaseTest {
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\org\\mateoRodriguez\\testData\\eCommerce_tc_1.json");
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }

    @Test(dataProvider = "getData")
    public void FillForm_ErrorMessage(HashMap<String, String> input) {

        /*formPage.countrySelection("Brazil");
        formPage.setNameField("");
        formPage.setGender("Female");*/
        //formPage.fillForm("Brazil", "", "Female");
        formPage.fillForm(input.get("country"), "", input.get("gender"));
        formPage.pressShopButton();
        Assert.assertEquals(formPage.getToastMessageError(), "Please enteer your name");

    }

    @Test(dataProvider = "getData")
    public void FillForm_PositiveFlow(HashMap<String, String> input) {

        //formPage.fillForm("Brazil", "PositiveFlow", "Female");
        formPage.fillForm(input.get("country"), input.get("name"), input.get("gender"));
        formPage.pressShopButton();
        Assert.assertTrue(formPage.getToastDisplayed() < 1);

    }
}
