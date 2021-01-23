package net.sadcenter.commons.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

@RequiredArgsConstructor
@Getter
public class CommandException extends Exception {

    private final String errorMessage;

}
