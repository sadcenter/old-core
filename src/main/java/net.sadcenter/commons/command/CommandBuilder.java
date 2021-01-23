package net.sadcenter.commons.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.sadcenter.commons.command.builders.impl.ArgumentBuilder;
import net.sadcenter.commons.command.builders.impl.PlayerBuilder;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 08.10.2020
 * @project LASTCORE
 */

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommandBuilder {

    private final String[] args;
    private final CommandSender executor;

    public static CommandBuilder start(String[] args, CommandSender executor) {
        return new CommandBuilder(args, executor);
    }

    public PlayerBuilder newPlayerBuilder() {
        return new PlayerBuilder(args);
    }

    public ArgumentBuilder newArgumentsBuilder() {
        return new ArgumentBuilder(args, executor);
    }


}

