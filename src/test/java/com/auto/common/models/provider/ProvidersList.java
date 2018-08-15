package com.auto.common.models.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "function", "secondaryList", "providersList", "primaryList" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersList {

    @JsonProperty("status")
    private String status;

    @JsonProperty("function")
    private String function;

    @JsonProperty("secondaryList")
    private List<MvpdList> secondaryList;

    @JsonProperty("providersList")
    private List<MvpdList> providersList;

    @JsonProperty("primaryList")
    private List<MvpdList> primaryList;

    public List<MvpdList> getSecondaryList() {
        return secondaryList;
    }

    public List<MvpdList> getProvidersList() {
        return providersList;
    }

    public List<MvpdList> getPrimaryList() {
        return primaryList;
    }
}
