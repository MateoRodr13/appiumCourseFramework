package org.mateoRodriguez.testUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.mateoRodriguez.pageObjects.android.FormPage;
import org.mateoRodriguez.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\mateoRodriguez\\resources\\data.properties");

        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress"): prop.getProperty("ipAddress");
        prop.load(fis);
        //String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        //Start server appium
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        //AndroidDriver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("androidDeviceName"));//Emulador
        //options.setDeviceName("DI59BA6HJBLB4LDU");//Real device
        options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\org\\mateoRodriguez\\resources\\apps\\General-Store.apk");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //First screen object
        formPage = new FormPage(driver);

    }

    @BeforeMethod(alwaysRun = true)
    public void preSetup(){
        formPage.setActivity();
    }

    @AfterMethod(alwaysRun = true)
    public void postSetup(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //Stop driver & server appium
        driver.quit();
        service.stop();
    }

}
