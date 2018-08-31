package com.auto.common.services.jscommands.commands;

/**
 * Created by veronica_lapunka on 8/29/18.
 */
public interface ICommand {

    <T> T execute(Object... args);

    String build();

}
