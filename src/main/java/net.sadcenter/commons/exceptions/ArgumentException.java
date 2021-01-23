package net.sadcenter.commons.exceptions;

import net.sadcenter.commons.command.CommandException;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

public class ArgumentException extends CommandException {

    public ArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
