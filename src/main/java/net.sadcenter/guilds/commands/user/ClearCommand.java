package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author sadcenter on 09.10.2020
 * @project LASTCORE
 */

public class ClearCommand extends Command {

    public ClearCommand() {
        super("clear", "scode.cmd.clear", true);
    }

    @Override
    public void onCommand(CommandSender player, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player toClear = commandBuilder
                .newPlayerBuilder()
                .getPlayer(1)
                .orElse((Player) player)
                .throwIf(new PlayerNullException(), cmdBuilder -> args.length == 1 && cmdBuilder.beforeBuild() == null)
                .build();

        ChatHelper.sendMessage(toClear, "&aTwoj ekwipunek zostal wyczyszczony!");

        toClear.getInventory().clear();
        toClear.getInventory().setArmorContents(new ItemStack[]{null, null, null, null});
    }
}
