package com.auto.common.props;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public enum MobileOS {
    ANDROID("Android"),
    ANDROID_SIM("Android_Sim"),
    IOS("iOS"),
    IOS_SIM("iOS_Sim");

    private final String value;

    private MobileOS(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static MobileOS getEnumByString(String value) {
        MobileOS[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MobileOS mobileOS = var1[var3];
            if(value.equalsIgnoreCase(mobileOS.value)) {
                return mobileOS;
            }
        }

        return null;
    }
}