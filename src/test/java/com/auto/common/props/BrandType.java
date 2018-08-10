package com.auto.common.props;

/**
 * Created by veronica_lapunka on 8/2/18.
 */
public enum BrandType {
    COMEDYCENTRAL ("ComedyCentral"),
    MTV ("MTV"),
    SPIKE ("Spike"),
    VH1 ("VH1"),
    LOGO ("Logo"),
    BELLATOR ("Bellator"),
    BET ("BET");

    private final String value;

    private BrandType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
