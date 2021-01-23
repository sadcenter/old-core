package net.sadcenter.commons.exceptions.impl;

import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

public class PlayerNullException extends CommandException {


    public PlayerNullException() {
        super(ChatHelper.fixColor("&cNie znaleziono takiego gracza"));
    }

    public PlayerNullException(String message) {
        super(ChatHelper.fixColor(message));
    }
}
