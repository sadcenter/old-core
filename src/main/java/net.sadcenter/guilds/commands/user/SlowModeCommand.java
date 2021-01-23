package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.NotNumberException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.managers.ChatManager;
import org.bukkit.command.CommandSender;

/**
 * @author sadcenter on 10.10.2020
 * @project LASTCORE
 */

public class SlowModeCommand extends Command {

    public SlowModeCommand() {
        super("slowmode", "core.cmd.slowmode", new String[]{"slow", "slowdown"}, false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("/slow [seconds]"))
                .throwIfNotNumber(new NotNumberException(1), 1);

        ChatManager.SLOW = Integer.parseInt(args[0]);
        ChatHelper.sendMessage(executor, "&aUstawiles slowdown na " + args[0]);
    }
}
