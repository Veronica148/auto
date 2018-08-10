package com.auto.common.models.intProvider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IntProvider {
    private String name;
    private String mvpd_id;
    @JsonProperty("authN_authZ_credential")
    private List<IntTVECredential> authN_authZ_credential;
    @JsonProperty("authN_NoauthZ_credential")
    private List<IntTVECredential> authN_NoauthZ_credential;
    private String usernameFieldIOS;
    private String passwordFieldIOS;
    private String submitButtonIOS;
    private String usernameFieldIOS_10;
    private String passwordFieldIOS_10;
    private String submitButtonIOS_10;
    private String usernameFieldAndroid;
    private String passwordFieldAndroid;
    private String submitButtonAndroid;
    private String tileLocatorSuffix;
    private String continueAndroid;
    private String continueIOS;
    private String continueIOS_10;
    private String secPasswordFieldAndroid;
    private String secPasswordFieldIOS_10;
    private String secPasswordFieldIOS;

    public String getTileLocatorSuffix() {
        return tileLocatorSuffix;
    }

    public String getUsernameFieldAndroid() {
        return usernameFieldAndroid;
    }

    public void setUsernameFieldAndroid(String usernameFieldAndroid) {
        this.usernameFieldAndroid = usernameFieldAndroid;
    }

    public String getPasswordFieldAndroid() {
        return passwordFieldAndroid;
    }

    public void setPasswordFieldAndroid(String passwordFieldAndroid) {
        this.passwordFieldAndroid = passwordFieldAndroid;
    }

    public String getSubmitButtonAndroid() {
        return submitButtonAndroid;
    }

    public void setSubmitButtonAndroid(String submitButtonAndroid) {
        this.submitButtonAndroid = submitButtonAndroid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String mvpd_id() {
        return mvpd_id;
    }

    public void setMvpd_id(String mvpd_id) {
        this.mvpd_id = mvpd_id;
    }

    public List<IntTVECredential> getAuthNAuthZCredential() {
        return authN_authZ_credential;
    }

    public List<IntTVECredential> getAuthNnoAuthZCredential() {
        return authN_NoauthZ_credential;
    }

    public void setAuthNAuthZCredential(List<IntTVECredential> authN_authZ_credential) {
        this.authN_authZ_credential = authN_authZ_credential;
    }

    public String getUsernameFieldIOS() {
        return usernameFieldIOS;
    }

    public void setUsernameFieldIOS(String usernameFieldIOS) {
        this.usernameFieldIOS = usernameFieldIOS;
    }

    public String getPasswordFieldIOS() {
        return passwordFieldIOS;
    }

    public void setPasswordFieldIOS(String passwordFieldIOS) {
        this.passwordFieldIOS = passwordFieldIOS;
    }

    public String getSubmitButtonIOS() {
        return submitButtonIOS;
    }

    public void setSubmitButtonIOS(String submitButtonIOS) {
        this.submitButtonIOS = submitButtonIOS;
    }

    public String getContinueAndroid() {
        return continueAndroid;
    }

    public void setContinueAndroid(String continueAndroid) {
        this.continueAndroid = continueAndroid;
    }

    public String getContinueIOS() {
        return continueIOS;
    }

    public String getUsernameFieldIOS_10() {
        return usernameFieldIOS_10;
    }

    public void setUsernameFieldIOS_10(String usernameFieldIOS_10) {
        this.usernameFieldIOS_10 = usernameFieldIOS_10;
    }

    public String getPasswordFieldIOS_10() {
        return passwordFieldIOS_10;
    }

    public void setPasswordFieldIOS_10(String passwordFieldIOS_10) {
        this.passwordFieldIOS_10 = passwordFieldIOS_10;
    }

    public String getSubmitButtonIOS_10() {
        return submitButtonIOS_10;
    }

    public void setSubmitButtonIOS_10(String submitButtonIOS_10) {
        this.submitButtonIOS_10 = submitButtonIOS_10;
    }

    public String getContinueIOS_10() {
        return continueIOS_10;
    }

    public void setContinueIOS_10(String continueIOS_10) {
        this.continueIOS_10 = continueIOS_10;
    }

    public String getSecPasswordFieldIOS_10() {
        return secPasswordFieldIOS_10;
    }

    public String getSecPasswordFieldIOS() {
        return secPasswordFieldIOS;
    }

    public String getSecPasswordFieldAndroid() {
        return secPasswordFieldAndroid;
    }
}
