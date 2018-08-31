package com.auto.common.services.jscommands.commands;

import org.openqa.selenium.WebDriver;

/**
 * Created by veronica_lapunka on 8/29/18.
 */
public class GetReadyStatus extends Command<String> {

    public GetReadyStatus(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(Object... args) {
        return super.execute(args).toString();
    }

    @Override
    public String build() {
        return String.format(com.auto.common.services.jscommands.Commands.GET_READY_STATE.getCommandString());
    }

}
