package net.sadcenter.commons.exceptions.impl;

import net.sadcenter.commons.exceptions.ArgumentException;
import net.sadcenter.commons.helper.ChatHelper;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

public class NotNumberException extends ArgumentException {

    public NotNumberException(int arg) {
        super(ChatHelper.fixColor("&7Argument &3" + arg + " &7nie jest liczba!"));
    }
}
