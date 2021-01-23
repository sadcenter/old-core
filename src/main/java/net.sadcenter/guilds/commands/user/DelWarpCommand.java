package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Warp;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * @author sadcenter on 15.09.2020
 * @project LASTCORE
 */

public class DelWarpCommand extends Command {

    public DelWarpCommand() {
        super("delwarp", "core.cmd.delwarp", false);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("warp [nazwa]"));

        Optional<Warp> warp = LastCore.getInstance().getConfiguration().getStorage().getWarp(args[0]);
        if (!warp.isPresent()) {
            ChatHelper.sendMessage(executor, "&cTaki warp nie istnieje");
            return;
        }
        LastCore.getInstance().getConfiguration().getStorage().getWarps().remove(args[0]);
        ChatHelper.sendMessage(executor, "&cUsunales warp!");
    }
}
