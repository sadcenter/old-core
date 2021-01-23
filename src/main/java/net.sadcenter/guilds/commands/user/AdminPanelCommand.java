package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.guilds.guis.AdminPanelGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */

public class AdminPanelCommand extends Command {

    public AdminPanelCommand() {
        super("adminpanel", "core.cmd.adminpanel", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        player.openInventory(new AdminPanelGui(player).build());
    }
}
