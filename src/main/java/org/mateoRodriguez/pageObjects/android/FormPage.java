package org.mateoRodriguez.pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mateoRodriguez.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement spinnerCountry;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    @AndroidFindBy(xpath="//android.widget.Toast[1]")
    private WebElement toastMessageError;

    @AndroidFindBy(xpath="//android.widget.Toast[1]")
    private List<WebElement> toastMessageErrorList;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setActivity(){
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"
        ));
    }

    public void setGender(String gender){
        if (gender.contains("Female"))
            femaleOption.click();
        else if (gender.contains("Male"))
            maleOption.click();
    }

    public void countrySelection(String country){
        spinnerCountry.click();
        scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + country + "\"]"))
                .click();
    }

    public ProductCatalog pressShopButton(){
        shopButton.click();
        return new ProductCatalog(driver);
    }

    public String getToastMessageError(){
        String toastMessageErrorString = toastMessageError.getAttribute("text");
        return toastMessageErrorString;
    }

    public int getToastDisplayed(){
        int countToast = toastMessageErrorList.size();
        return countToast;
    }

    public void  fillForm(String country, String name, String gender){
        countrySelection(country);
        setNameField(name);
        setGender(gender);
    }
}
