package org.mateoRodriguez;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.mateoRodriguez.testUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_tc_1 extends AndroidBaseTest {

    /*@BeforeMethod()
    public void preSetup(){
        formPage.setActivity();
    }
    @AfterMethod
    public void postSetup(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }*/

    @Test
    public void FillForm_ErrorValidation() throws InterruptedException {

        /*driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
                .sendKeys("Mateo Rodriguez");
        driver.hideKeyboard();*/
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale"))
                .click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"))
                .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Aruba\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"))
                .click();
        Thread.sleep(3000);
        String toastMessage =  driver.findElement(By.xpath("//android.widget.Toast [1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enterr your name");
    }

    @Test
    public void FillForm_PositiveFlow() throws InterruptedException {

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
                .sendKeys("Mateo Rodriguez");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale"))
                .click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"))
                .click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Colombia\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Colombia\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop"))
                .click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast [1]")).size() < 1);
    }
}
