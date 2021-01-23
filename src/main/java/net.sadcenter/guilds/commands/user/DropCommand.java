package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.guilds.guis.drop.DropMenuGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 03.09.2020
 * @project LASTCORE
 */

public class DropCommand extends Command {

    public DropCommand() {
        super("drop", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        player.openInventory(new DropMenuGui(player).build());
    }
}
