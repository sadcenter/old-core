package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.guilds.guis.DepositGui;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * @author sadcenter on 06.09.2020
 * @project LASTCORE
 */

public class DepositCommand extends Command {


    public DepositCommand() {
        super("schowek", Collections.singletonList("depozyt"), true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        player.openInventory(new DepositGui(player).build());
    }
}
