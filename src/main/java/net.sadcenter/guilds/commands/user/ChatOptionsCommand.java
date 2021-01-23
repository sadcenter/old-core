package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.guilds.guis.ChatOptionsGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 13.09.2020
 * @project LASTCORE
 */

public class ChatOptionsCommand extends Command {

    public ChatOptionsCommand() {
        super("cc", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        player.openInventory(new ChatOptionsGui(player).build());
    }
}
