package org.mateoRodriguez.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mateoRodriguez.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPriceList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termAndConditions;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement btnCloseTermAndConditions;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement emailCheckBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
        private WebElement btnProceedShop;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public Double getProductsSum(){
        int productCount = productPriceList.size();
        double totalPrice = 0;
        for (int i = 0; i < productCount; i++) {
            String amountString = productPriceList.get(i).getText();
            totalPrice += getFormattedAmount(amountString);
        }

        return totalPrice;
    }

    public Double getTotalAmountDisplayed(){
        String totalAmountString = totalAmount.getText();
        return getFormattedAmount(totalAmountString);
    }

    public void viewTermAndConditions(){
        longPressAction(termAndConditions);
    }

    public void closeTermAndConditions(){
        btnCloseTermAndConditions.click();
    }

    public void pressEmailCheckBox(){
        emailCheckBox.click();
    }

    public void pressBtnProceedShop(){
        btnProceedShop.click();
    }


}
