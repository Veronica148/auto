package com.auto.common.services.jscommands.commands;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by veronica_lapunka on 8/29/18.
 */
public abstract class Command<T> implements ICommand {

    protected static final Logger LOGGER = Logger.getLogger(Command.class);

    protected JavascriptExecutor jse;

    protected WebDriver driver;

    protected Command(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;

    }

    @SuppressWarnings("unchecked")
    @Override
    public T execute(Object... args) {
        String command = build();
        LOGGER.debug("Executting command: " + command);
        return (T) jse.executeScript(command, args);
    }

    @Override
    public abstract String build();
}
