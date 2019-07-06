package com.auto.common.utils;

import com.auto.common.driver.DriverType;
import com.auto.common.models.intProvider.BaseProvider;
import com.auto.common.props.BrandType;
import com.auto.common.props.DeviceCategory;
import com.auto.common.props.MobileOS;
import com.auto.common.utils.Config.ConfigProps;

/**
 * Created by veronica_lapunka on 8/1/18.
 */
public class TestRun {

    private static ThreadLocal<MobileOS> mobileOS = new ThreadLocal<MobileOS>();
    private static ThreadLocal<DeviceCategory> deviceCategory = new ThreadLocal<DeviceCategory>();
    private static ThreadLocal<String> appPackageID = new ThreadLocal<String>();
    private static ThreadLocal<String> androidLaunchActivity = new ThreadLocal<String>();
    private static ThreadLocal<String> appPackagePath = new ThreadLocal<String>();
    private static ThreadLocal<BrandType> brand = new ThreadLocal<BrandType>();
    private static ThreadLocal<DriverType> browser = new ThreadLocal<DriverType>();
    private static ThreadLocal<BaseProvider> baseProviderThreadLocal = new ThreadLocal<BaseProvider>();

    public static synchronized void init(String runParams) {
        // set the os
        setMobileOS(MobileOS.valueOf(ConfigProps.OS.toUpperCase()));

        // set the device type
        setDeviceCategory(DeviceCategory.valueOf(ConfigProps.DEVICE_CATEGORY.toUpperCase()));

        //set browser
        setBrowser(DriverType.valueOf(ConfigProps.BROWSER.toUpperCase()));

        // set the app package
        if (isAndroid()) {
            setAppPackageID(ConfigProps.TVEDEMO_ANDROID_APP_PACKAGE);
            setAndroidLaunchActivity(ConfigProps.TVEDEMO_ANDROID_LAUNCH_ACTIVITY);
        } else {
            //Do smth for Web setAppPackageID(ConfigProps.TVEDEMO_IOS_BUNDLE_ID);
        }
    }

    public static synchronized void setMobileOS(MobileOS os) {
        mobileOS.set(os);
    }

    public static synchronized void setBrowser(DriverType brow) {
        browser.set(brow);
    }


    public static synchronized MobileOS getMobileOS() {
        return mobileOS.get();
    }

    public static synchronized void setDeviceCategory(DeviceCategory device) {
        deviceCategory.set(device);
    }

    public static synchronized DeviceCategory getDeviceCategory() {
        return deviceCategory.get();
    }

    public static synchronized void setAppPackageID(String id) {
        appPackageID.set(id);
    }

    public static synchronized String getAppPackageID() {
        return appPackageID.get();
    }

    public static synchronized void setAndroidLaunchActivity(String activity) {
        androidLaunchActivity.set(activity);
    }

    public static synchronized String getAndroidLaunchActivity() {
        return androidLaunchActivity.get();
    }

    public static synchronized void setAppPackagePath(String path) {
        appPackagePath.set(path);
    }

    public static synchronized String getAppPackagePath() {
        return appPackagePath.get();
    }

    public static synchronized Boolean isIos() {
        return getMobileOS().equals(MobileOS.IOS);
    }

    public static synchronized Boolean isAndroid() {
        return getMobileOS().equals(MobileOS.ANDROID);
    }

    public static synchronized Boolean isPhone() {
        return getDeviceCategory().equals(DeviceCategory.PHONE);
    }

    public static synchronized Boolean isTablet() {
        return getDeviceCategory().equals(DeviceCategory.TABLET);
    }

    public static BrandType getBrand() {
        return brand.get();
    }

    public static DriverType getBrowser() {
        return browser.get();
    }

    public static synchronized void setBaseProvider(BaseProvider baseProvider) {
        baseProviderThreadLocal.set(baseProvider);
    }

    public static synchronized BaseProvider getBaseProvider() {
        return baseProviderThreadLocal.get();
    }

}
