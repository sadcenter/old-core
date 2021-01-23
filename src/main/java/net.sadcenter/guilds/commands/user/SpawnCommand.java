package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.guilds.LastCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */
public class SpawnCommand extends Command {

    public SpawnCommand() {
        super("spawn", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        LastCore.getInstance().getTeleportManager().add(player, player.getWorld().getSpawnLocation());
    }
}
