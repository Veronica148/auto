package com.auto.common.utils;

/**
 * Created by veronica_lapunka on 8/17/18.
 */
public interface Function <F, T> {
    public  T apply(F var1);

    boolean equals(Object var1);
}
