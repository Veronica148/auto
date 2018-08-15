package com.auto.common.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "isFreePreview",
        "altName",
        "authStandart",
        "isPrimary",
        "displayName",
        "defaultLogoUrl",
        "logoutLogoUrl",
        "pickerLogoUrl",
        "cobrandingLogoUrl",
        "isCustomAuthZErrorMessageEnabled",
        "blackLogoURL",
        "whiteLogoURL",
        "colorLogoURL"
})
public class MvpdList {

    @JsonProperty("id")
    private String id;
    @JsonProperty("isFreePreview")
    private Boolean isFreePreview;
    @JsonProperty("altName")
    private String altName;
    @JsonProperty("authStandard")
    private String authStandard;
    @JsonProperty("isPrimary")
    private Boolean isPrimary;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("defaultLogoUrl")
    private String defaultLogoUrl;
    @JsonProperty("logoutLogoUrl")
    private String logoutLogoUrl;
    @JsonProperty("pickerLogoUrl")
    private String pickerLogoUrl;
    @JsonProperty("cobrandingLogoUrl")
    private String cobrandingLogoUrl;
    @JsonProperty("isCustomAuthZErrorMessageEnabled")
    private Boolean isCustomAuthZErrorMessageEnabled;
    @JsonProperty("blackLogoURL")
    private String blackLogoURL;
    @JsonProperty("whiteLogoURL")
    private String whiteLogoURL;
    @JsonProperty("colorLogoURL")
    private String colorLogoURL;

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The isFreePreview
     */
    @JsonProperty("isFreePreview")
    public Boolean getIsFreePreview() {
        return isFreePreview;
    }

    /**
     *
     * @param isFreePreview
     * The isFreePreview
     */
    @JsonProperty("isFreePreview")
    public void setIsFreePreview(Boolean isFreePreview) {
        this.isFreePreview = isFreePreview;
    }

    /**
     *
     * @return
     * The altName
     */
    @JsonProperty("altName")
    public String getAltName() {
        return altName;
    }

    /**
     *
     * @param altName
     * The altName
     */
    @JsonProperty("altName")
    public void setAltName(String altName) {
        this.altName = altName;
    }

    /**
     *
     * @return
     * The authStandart
     */
    @JsonProperty("authStandard")
    public String getAuthStandart() {
        return authStandard;
    }

    /**
     *
     * @param authStandart
     * The authStandart
     */
    @JsonProperty("authStandard")
    public void setAuthStandart(String authStandart) {
        this.authStandard = authStandart;
    }

    /**
     *
     * @return
     * The isPrimary
     */
    @JsonProperty("isPrimary")
    public Boolean getIsPrimary() {
        return isPrimary;
    }

    /**
     *
     * @param isPrimary
     * The isPrimary
     */
    @JsonProperty("isPrimary")
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     *
     * @return
     * The displayName
     */
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The displayName
     */
    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The defaultLogoUrl
     */
    @JsonProperty("defaultLogoUrl")
    public String getDefaultLogoUrl() {
        return defaultLogoUrl;
    }

    /**
     *
     * @param defaultLogoUrl
     * The defaultLogoUrl
     */
    @JsonProperty("defaultLogoUrl")
    public void setDefaultLogoUrl(String defaultLogoUrl) {
        this.defaultLogoUrl = defaultLogoUrl;
    }

    /**
     *
     * @return
     * The logoutLogoUrl
     */
    @JsonProperty("logoutLogoUrl")
    public String getLogoutLogoUrl() {
        return logoutLogoUrl;
    }

    /**
     *
     * @param logoutLogoUrl
     * The logoutLogoUrl
     */
    @JsonProperty("logoutLogoUrl")
    public void setLogoutLogoUrl(String logoutLogoUrl) {
        this.logoutLogoUrl = logoutLogoUrl;
    }

    /**
     *
     * @return
     * The pickerLogoUrl
     */
    @JsonProperty("pickerLogoUrl")
    public String getPickerLogoUrl() {
        return pickerLogoUrl;
    }

    /**
     *
     * @param pickerLogoUrl
     * The pickerLogoUrl
     */
    @JsonProperty("pickerLogoUrl")
    public void setPickerLogoUrl(String pickerLogoUrl) {
        this.pickerLogoUrl = pickerLogoUrl;
    }

    /**
     *
     * @return
     * The cobrandingLogoUrl
     */
    @JsonProperty("cobrandingLogoUrl")
    public String getCobrandingLogoUrl() {
        return cobrandingLogoUrl;
    }

    /**
     *
     * @param cobrandingLogoUrl
     * The cobrandingLogoUrl
     */
    @JsonProperty("cobrandingLogoUrl")
    public void setCobrandingLogoUrl(String cobrandingLogoUrl) {
        this.cobrandingLogoUrl = cobrandingLogoUrl;
    }

    /**
     *
     * @return
     * The isCustomAuthZErrorMessageEnabled
     */
    @JsonProperty("isCustomAuthZErrorMessageEnabled")
    public Boolean getIsCustomAuthZErrorMessageEnabledl() {
        return isCustomAuthZErrorMessageEnabled;
    }

    /**
     *
     * @param isCustomAuthZErrorMessageEnabled
     * The isCustomAuthZErrorMessageEnabled
     */
    @JsonProperty("isCustomAuthZErrorMessageEnabled")
    public void setIsCustomAuthZErrorMessageEnabled(Boolean isCustomAuthZErrorMessageEnabled) {
        this.isCustomAuthZErrorMessageEnabled = isCustomAuthZErrorMessageEnabled;
    }

    @JsonProperty("blackLogoURL")
    public String getBlackLogoURL() {
        return blackLogoURL;
    }

    @JsonProperty("whiteLogoURL")
    public String getWhiteLogoURL() {
        return whiteLogoURL;
    }

    @JsonProperty("colorLogoURL")
    public String getColorLogoURL() {
        return colorLogoURL;
    }

    @Override
    public String toString() {
        return "MvpdList{" +
                "id='" + id + '\'' +
                ", isFreePreview=" + isFreePreview +
                ", altName='" + altName + '\'' +
                ", authStandard='" + authStandard + '\'' +
                ", isPrimary=" + isPrimary +
                ", displayName='" + displayName + '\'' +
                ", defaultLogoUrl='" + defaultLogoUrl + '\'' +
                ", logoutLogoUrl='" + logoutLogoUrl + '\'' +
                ", pickerLogoUrl='" + pickerLogoUrl + '\'' +
                ", cobrandingLogoUrl='" + cobrandingLogoUrl + '\'' +
                ", isCustomAuthZErrorMessageEnabled=" + isCustomAuthZErrorMessageEnabled +
                ", blackLogoURL='" + blackLogoURL + '\'' +
                ", whiteLogoURL='" + whiteLogoURL + '\'' +
                ", colorLogoURL='" + colorLogoURL + '\'' +
                '}';
    }
}
