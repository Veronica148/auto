package com.auto.common.models.intProvider;

public class BaseProvider {

    private String locale;
    private String brand;
    private String providerName;
    private String environment;
    private String device;

    public BaseProvider(String locale, String brand, String providerName, String environment, String device) {
        this.locale = locale;
        this.brand = brand;
        this.providerName = providerName;
        this.environment = environment;
        this.device = device;
    }

    public String getLocale() {
        return locale;
    }

    public String getBrand() {
        return brand;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getDevice() {
        return device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseProvider that = (BaseProvider) o;

        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (providerName != null ? !providerName.equals(that.providerName) : that.providerName != null) return false;
        if (environment != null ? !environment.equals(that.environment) : that.environment != null) return false;
        return device != null ? device.equals(that.device) : that.device == null;
    }

    @Override
    public int hashCode() {
        int result = locale != null ? locale.hashCode() : 0;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (providerName != null ? providerName.hashCode() : 0);
        result = 31 * result + (environment != null ? environment.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return locale + '_' + brand + '_' + providerName + '_' + environment + '_' + device;
    }
}
