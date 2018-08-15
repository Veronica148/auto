package com.auto.common.models.provider;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "mvpdList"
})
public class ProviderInfo {

    @JsonAlias({"mvpdList","providerInfo"})
    private MvpdList mvpdList;

    /**
     *
     * @return
     * The mvpdList
     */
    @JsonAlias({"mvpdList","providerInfo"})
    public MvpdList getMvpdList() {
        return mvpdList;
    }

    /**
     *
     * @param mvpdList
     * The mvpdList
     */
    @JsonAlias({"mvpdList","providerInfo"})
    public void setMvpdList(MvpdList mvpdList) {
        this.mvpdList = mvpdList;
    }

}
