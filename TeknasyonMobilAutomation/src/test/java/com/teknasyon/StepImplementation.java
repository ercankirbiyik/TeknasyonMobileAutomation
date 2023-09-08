package com.teknasyon;

import com.teknasyon.helper.RandomString;
import com.teknasyon.helper.StoreHelper;
import com.teknasyon.model.SelectorInfo;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepImplementation extends HookImplementation {

    public Logger logger = LoggerFactory.getLogger(getClass());
    private List<Integer> integerList=new ArrayList<>();
    private  List<String> stringList=new ArrayList<>();
    private AndroidDriver<MobileElement> driver;
    private static HashMap<String, String> userVariableHashMap = new HashMap<>();
    private static String kaydedilmisNumara;

    public List<MobileElement> findElements(By by) throws Exception {
        List<MobileElement> webElementList = null;
        try {
            webElementList = appiumFluentWait.until(new ExpectedCondition<List<MobileElement>>() {
                @Nullable
                @Override
                public List<MobileElement> apply(@Nullable WebDriver driver) {
                    List<MobileElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });
            if (webElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }
        } catch (Exception e) {
            throw e;
        }
        return webElementList;
    }

    public List<MobileElement> findElementsWithoutAssert(By by) {

        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {
        }
        return mobileElements;
    }

    public List<MobileElement> findElementsWithAssert(By by) {

        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(by);
        } catch (Exception e) {
            Assertions.fail("by = %s Elements not found ", by.toString());
            e.printStackTrace();
        }
        return mobileElements;
    }


    public MobileElement findElement(By by) throws Exception {
        MobileElement mobileElement;
        try {
            mobileElement = findElements(by).get(0);
        } catch (Exception e) {
            throw e;
        }
        return mobileElement;
    }

    public MobileElement findElementWithoutAssert(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementWithAssertion(By by) {
        MobileElement mobileElement = null;
        try {
            mobileElement = findElement(by);
        } catch (Exception e) {
            Assertions.fail(mobileElement.getAttribute("value") + " " + "by = %s Element not found ", by.toString());
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElement;
    }

    public MobileElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);

        MobileElement mobileElement = null;
        try {
            mobileElement = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Element not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElement;
    }


    public List<MobileElement> findElemenstByKeyWithoutAssert(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElements;
    }

    public List<MobileElement> findElemenstByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<MobileElement> mobileElements = null;
        try {
            mobileElements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            Assertions.fail("key = %s by = %s Elements not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return mobileElements;
    }

    public void pointToPointSwipeForDayAndYear(int startX, int startY, int endX, int endY, int count) throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;
        if (count > 200) {
            startX = (width * startX) / 100;
            startY = (height * startY) / 100;
            endX = (width * endX) / 100;
            endY = (height * endY) / 100;
            count = count - 2019;

        } else
            count--;
        for (int i = 0; i < count; i++) {
            waitBySecond(1);
            TouchAction action = new TouchAction(appiumDriver);
            action.press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(endX, endY))
                    .release().perform();
        }
    }

    private void backPage() {
        appiumDriver.navigate().back();
    }


    private String getCapability(String text) {
        return appiumDriver.getCapabilities().getCapability(text).toString();

    }

    public boolean doesElementExistByKey(String key, int time) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        try {
            WebDriverWait elementExist = new WebDriverWait(appiumDriver, time);
            elementExist.until(ExpectedConditions.visibilityOfElementLocated(selectorInfo.getBy()));
            return true;
        } catch (Exception e) {
            logger.info(key + " elementi araniyor");
            System.out.println("-----------------------------------------------------------------");

            return false;
        }
    }

    private int getScreenWidth() {
        return appiumDriver.manage().window().getSize().width;
    }

    private int getScreenHeight() {
        return appiumDriver.manage().window().getSize().height;
    }

    private int getScreenWithRateToPercent(int percent) {
        return getScreenWidth() * percent / 100;
    }

    private int getScreenHeightRateToPercent(int percent) {
        return getScreenHeight() * percent / 100;
    }

    public void justSwipe() {
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point(500, 2000)).moveTo(PointOption.point(500, 500)).release().perform();
    }

    public void swipeDownAccordingToPhoneSize(int startXLocation, int startYLocation, int endXLocation, int endYLocation) {
        startXLocation = getScreenWithRateToPercent(startXLocation);
        startYLocation = getScreenHeightRateToPercent(startYLocation);
        endXLocation = getScreenWithRateToPercent(endXLocation);
        endYLocation = getScreenHeightRateToPercent(endYLocation);

        new TouchAction(appiumDriver)
                .press(PointOption.point(startXLocation, startYLocation))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endXLocation, endYLocation))
                .release()
                .perform();
    }

    public void swipeUpAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;
            System.out.println(width + "  " + height);

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 20) / 100;
            int swipeEndHeight = (height * 80) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction((AndroidDriver) appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 35) / 100;
            int swipeEndHeight = (height * 75) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        }
    }

    public void swipeTwoUpAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;
            System.out.println(width + "  " + height);

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 10) / 100;
            int swipeEndHeight = (height * 60) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction((AndroidDriver) appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 25) / 100;
            int swipeEndHeight = (height * 60) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeEndHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(2000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeStartHeight))
                    .release()
                    .perform();
        }
    }

    public void swipeDownAccordingToPhoneSize() {
        if (appiumDriver instanceof AndroidDriver) {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 90) / 100;
            int swipeEndHeight = (height * 50) / 100;
            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else {
            Dimension d = appiumDriver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            int swipeStartWidth = width / 2, swipeEndWidth = width / 2;
            int swipeStartHeight = (height * 90) / 100;
            int swipeEndHeight = (height * 40) / 100;
            // appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        }
    }

    public boolean isElementPresent(By by) {
        return findElementWithoutAssert(by) != null;
    }


    @Step({"<key> Elementine tıkla",
            "Click element <key>"})
    public void clickByKey(String key) {
        doesElementExistByKey(key, 5);
        findElementByKey(key).click();
        logger.info(key + " elementine tıkladı");
        System.out.println("-----------------------------------------------------------------");
    }


    @Step({"<key> elementine <clickCount> kez tıkla",
            "Click the <key> element <clickCount> times"})
    public void tapElementMultipleTimes(String key, int clickCount) {
        doesElementExistByKey(key, 5);

        for (int i = 0; i < clickCount; i++) {
            findElementByKey(key).click();
            logger.info("Clicked the element " + key + " " + clickCount + " times.");
        }
    }

    @Step({"<key> elementinin görünürlüğü kontrol edilir",
            "Check if element <key> exist"})
    public void existElement(String key) {
        assertTrue(findElementByKey(key).isDisplayed(), "Element sayfada bulunamadı !");
        logger.info(key + " elementi göründü");
        System.out.println("-----------------------------------------------------------------");

    }
    @Step({"<key> elementinin olmadigi kontrol edilir",
            "Check that the <key> element is not visible"})
    public void notExistElement(String key) {
        assertFalse(doesElementExistByKey(key,10), "Element mevcut !");
        logger.info("Elementin olmadığı doğrulandı");
    }

    @Step({"<key> elementinin <text> textini içerdiği kontrol edilir",
            "Check that the <key> element contains the text <text>"})
    public void checkTextByKey(String key, String text) {
        try {
            Thread.sleep(3000);
            assertTrue(findElementByKey(key).getText().contains(text), "Element beklenen değeri taşımıyor !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step({"<key> li elementi bul, temizle ve <text> değerini yaz",
            "Find element by <key> clear and send keys <text>"})
    public void sendKeysByKey(String key, String text) {
        MobileElement webElement = findElementByKey(key);
        webElement.clear();
        webElement.setValue(text);
    }

    //Step NullPointerException atıyor. TouchAction() fonksiyonunda
    // driverın doğru şekilde çağırılması lazım
    @Step({"<key> elementine <second> saniye boyunca basılı tut",
            "Press and hold the element <key> for <second> seconds"})
    public void pressElementForDuration(String key, int second) throws InterruptedException {
        MobileElement element = findElementByKey(key);
        int milliseconds = second * 1000;
        TouchAction action = new TouchAction(appiumDriver);
        action.longPress(ElementOption.element(element))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliseconds)))
                .release()
                .perform();
        logger.info("Pressed and hold the element " + key + " for " + second + " seconds.");
    }

    @Step({"<key> li elementi bul ve değerini <saveKey> olarak sakla",
            "Find element by <key> and save text as <saveKey>"})
    public void saveTextByKey(String key, String saveKey) {
        StoreHelper.INSTANCE.saveValue(saveKey, findElementByKey(key).getText());
        logger.info("The text of the saved is: " + saveKey);
    }

    @Step({"<key> li element saklanan <saveKey> değerini içeriyor mu kontrol et",
            "Check that the element <key> contains the saved attribute <saveKey>"})
    public void checkTheContainsSaveKey(String key, String saveKey){
        boolean containsText = findElementByKey(key).getText().contains(StoreHelper.INSTANCE.getValue(saveKey));
        assertTrue(containsText, "Expected text is not contained!");
        logger.info("Expected text is contained.");
    }

    @Step({"<times> kere aşağıya kaydır",
            "Swipe down <times> times"})
    public void swipe(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            swipeDownAccordingToPhoneSize();
            waitBySecond(3);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWİPE EDİLDİ");
            System.out.println("-----------------------------------------------------------------");

        }
    }

    @Step({"<times> kere hafif yukarı doğru kaydır",
            "Swipe up times <times>"})
    public void swipeUPTwo(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            swipeTwoUpAccordingToPhoneSize();
            waitBySecond(1);
            System.out.println("-----------------------------------------------------------------");
            System.out.println("SWİPE EDİLDİ");
            System.out.println("-----------------------------------------------------------------");

        }
    }


    @Step({"Değeri <text> e eşit olan elementi bul ve tıkla",
            "Find element text equals <text> and click"})
    public void clickByText(String text) {
        findElementWithAssertion(By.xpath(".//*[contains(@text,'" + text + "')]")).click();
    }

    @Step({"İçeriği <value> e eşit olan elementli bul ve tıkla",
            "Find element value equals <value> and click"})
    public void clickByValue(String value) {
        findElementWithAssertion(MobileBy.xpath(".//*[contains(@value,'" + value + "')]")).click();
    }



    @Step({"<key> elementi aktif değil mi kontrol et",
            "Check element <key> is not active"})
    public void checkElementIsNotActive(String key) {
        MobileElement element = findElementByKey(key);
        boolean isNotActive = !element.isEnabled();
        logger.info("The element " + key + " is not active.");
    }

    @Step({"<n> tane <key> elementi var mı kontrol et",
            "Check for <n> <key> elements"})
    public void checkNumberOfElementsWithText(int n, String key) {
        List<MobileElement> elements = findElemenstByKey(key);
        int actualCount = elements.size();

        if (actualCount == n) {
            logger.info("Expected count (" + n + ") matches the actual count (" + actualCount + ")");
        } else {
            logger.info("Expected count (" + n + ") does not match the actual count (" + actualCount + ")");
        }
    }


    @Step({"Open the notification bar area"})
    public void openTheNotification(){
        driver.openNotifications();
        logger.info("Notification bar opened.");
    }


    @Step({"Bir önceki sayfaya dönülür",
            "Returns to the previous page"})
    public void returnThePreviousPage(){
        driver.navigate().back();
    }


    @Step({"<key> elementi aktif mi kontrol et",
            "Check element <key> is active"})
    public void checkElementIsActive(String key) {
        findElementByKey(key).isEnabled();
        logger.info("The element " + key + " is active.");
    }
    @Step({"<seconds> saniye bekle",
            "Wait <second> seconds"})
    public void waitBySecond(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            logger.info(seconds + " saniye beklendi");
            System.out.println("-----------------------------------------------------------------");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}