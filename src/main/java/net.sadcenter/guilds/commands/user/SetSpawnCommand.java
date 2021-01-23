package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

public class SetSpawnCommand extends Command {

    public SetSpawnCommand() {
        super("setspawn", "core.cmd.setspawn", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        ChatHelper.sendMessage(player, "&AUstawiles spawn!");
        Location location = player.getLocation();
        Bukkit.getWorld("world").setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
