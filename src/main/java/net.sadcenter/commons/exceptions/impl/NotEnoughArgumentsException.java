package net.sadcenter.commons.exceptions.impl;

import net.sadcenter.commons.exceptions.ArgumentException;
import net.sadcenter.commons.helper.ChatHelper;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

public class NotEnoughArgumentsException extends ArgumentException {

    public NotEnoughArgumentsException(String usage) {
        super(ChatHelper.fixColor("&7Poprawne uzycie: &3/" + usage));
    }
}
