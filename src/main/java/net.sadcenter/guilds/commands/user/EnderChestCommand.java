package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */

public class EnderChestCommand extends Command {

    public EnderChestCommand() {
        super("ec", "core.cmd.vip", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        player.openInventory(player.getEnderChest());
    }
}
