package com.auto.common.models.intProvider;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.auto.common.constants.EnvironmentConstants.PRODUCTION;
import static com.auto.common.constants.EnvironmentConstants.STAGING;

public class IntTVECredential {
    
    @JsonProperty("locale")
    private String locale;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("mvpd_id")
    private String mvpd_id;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("brands")
    private String brands;

    public List<String> getBrands() {
        if (null == brands)
            return Collections.emptyList();
        return Arrays.asList(brands.split("\\,"));
    }

    public String getBrand() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public List<String> getEnvironments() {
        if (environment == null) {
            return Arrays.asList(STAGING, PRODUCTION);
        } else {
            return Collections.singletonList(environment);
        }
    }

    public String getEnvironment() {
        return environment;
    }

    public boolean isValidFor(String environment) {
        return null == this.environment || this.environment.equalsIgnoreCase(environment);
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMvpd_id() {
        return mvpd_id;
    }

    public void setMvpd_id(String mvpd_id) {
        this.mvpd_id = mvpd_id;
    }
}
