package com.auto.common.services.jscommands.commands;

public enum Commands {

    GET_READY_STATE("return document.readyState");

    private String commandString;

    Commands(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }
}
