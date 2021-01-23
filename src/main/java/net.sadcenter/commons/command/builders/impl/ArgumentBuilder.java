package net.sadcenter.commons.command.builders.impl;

import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.command.builders.Builder;
import net.sadcenter.commons.exceptions.ArgumentException;
import net.sadcenter.commons.exceptions.impl.NotPlayerException;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

@RequiredArgsConstructor
public class ArgumentBuilder implements Builder {

    private final String[] args;
    private final CommandSender executor;

    public ArgumentBuilder throwError(int enoughArgs, ArgumentException exception) throws CommandException {
        if (args.length < enoughArgs) {
            throw exception;
        }
        return this;
    }

    public ArgumentBuilder onlyPlayerCommand() throws CommandException {
        if (executor instanceof Player) {
            throw new NotPlayerException();
        }
        return this;
    }

    public ArgumentBuilder setIf(String[] stringArray, String value, int arg, Predicate<ArgumentBuilder> predicate) {
        if (predicate.test(this))
            stringArray[arg - 1] = value;
        return this;
    }

    public ArgumentBuilder throwIfNotNumber(ArgumentException argumentException, int arg) throws CommandException {
        if (!NumberUtils.isNumber(args[arg - 1]))
            throw argumentException;

        return this;
    }

    public String[] getArgs() {
        return args;
    }
}
