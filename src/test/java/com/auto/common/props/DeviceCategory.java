package com.auto.common.props;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public enum DeviceCategory {
    PHONE("Phone"),
    TABLET("Tablet");

    private final String value;

    private DeviceCategory(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static DeviceCategory getEnumByString(String value) {
        DeviceCategory[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DeviceCategory deviceCategory = var1[var3];
            if(value.equalsIgnoreCase(deviceCategory.value)) {
                return deviceCategory;
            }
        }

        return null;
    }
}

