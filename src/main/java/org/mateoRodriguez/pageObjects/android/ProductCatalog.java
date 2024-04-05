package org.mateoRodriguez.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.mateoRodriguez.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement btnCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement toolbarTitle;

    public ProductCatalog(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addItemToCartByIndex(int index){
        addToCart.get(index).click();
    }

    public CartPage openCart(){
        btnCart.click();
        waitForSelectorAndText(toolbarTitle, "Cart", driver);
        return new CartPage(driver);
    }


}
