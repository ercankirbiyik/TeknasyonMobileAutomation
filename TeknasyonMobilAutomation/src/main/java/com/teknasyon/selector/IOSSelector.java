package com.teknasyon.selector;

import io.appium.java_client.MobileBy;
import com.teknasyon.model.ElementInfo;
import org.openqa.selenium.By;

public class IOSSelector implements Selector {

    @Override
    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        String iosType = elementInfo.getIosType();
        String iosValue = elementInfo.getIosValue();
        switch (iosType){
            case "css":
                by = MobileBy.cssSelector(iosValue);
                break;
            case "id":
                by = MobileBy.id(iosValue);
                break;
            case "xpath":
                by = MobileBy.xpath(iosValue);
                break;
            case "class":
                by = MobileBy.className(iosValue);
                break;
            case "name":
                by = MobileBy.name(iosValue);
                break;
            case "classChain":
                by = MobileBy.iOSClassChain(iosValue);
                break;
            case "accessibilityId":
                by = MobileBy.AccessibilityId(iosValue);
                break;
            default:
                throw new NullPointerException(elementInfo.getKey() + " keyine sahip elementin " + "\"" + iosType + "\"" + " ios tipi değeri uyuşmuyor.");
        }
        return by;
    }

    @Override
    public int getElementInfoToIndex(ElementInfo elementInfo) {
        return elementInfo.getIosIndex();
    }
}
