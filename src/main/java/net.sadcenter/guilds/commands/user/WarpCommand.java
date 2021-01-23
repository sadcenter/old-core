package net.sadcenter.guilds.commands.user;

import com.google.common.base.Joiner;
import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.LastCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 14.09.2020
 * @project LASTCORE
 */

public class WarpCommand extends Command {
    public WarpCommand() {
        super("warp", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        if (args.length == 0) {
            ChatHelper.sendMessage(executor, "&7Warpy: &3" + Joiner.on("&8, &3").join(LastCore.getInstance().getConfiguration().getStorage().getWarps().keySet().toArray()));
            return;
        }
        LastCore.getInstance().getConfiguration().getStorage().getWarp(args[0]).ifPresent(warp -> {
            LastCore.getInstance().getTeleportManager().add((Player) executor, warp.getLocation());
        });
    }
}
