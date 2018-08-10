package com.auto.common.models.intProvider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvidersType {
    @JsonProperty("providers")
    private List<IntProvider> providers;

    public List<IntProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<IntProvider> providers) {
        this.providers = providers;
    }
}
