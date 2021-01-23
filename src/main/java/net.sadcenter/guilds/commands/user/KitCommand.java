package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.guilds.guis.KitGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 05.09.2020
 * @project LASTCORE
 */

public class KitCommand extends Command {

    public KitCommand() {
        super("kit", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        player.openInventory(new KitGui(player).build());
    }
}
