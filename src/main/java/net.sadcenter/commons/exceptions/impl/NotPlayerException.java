package net.sadcenter.commons.exceptions.impl;

import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;

/**
 * @author sadcenter on 14.10.2020
 * @project LASTCORE
 */

public class NotPlayerException extends CommandException {

    public NotPlayerException() {
        super(ChatHelper.fixColor("&cNie jestes graczem!"));
    }
}
