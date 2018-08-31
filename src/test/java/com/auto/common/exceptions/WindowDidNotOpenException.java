package com.auto.common.exceptions;

/**
 * Created by veronica_lapunka on 8/17/18.
 */
public class WindowDidNotOpenException extends RuntimeException {

    private static final long serialVersionUID = -3582642038929788623L;

    public WindowDidNotOpenException(String message) {
        super(message);
    }

    public WindowDidNotOpenException(String message, Throwable cause) {
        super(message, cause);
    }
}
