package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

/**
 * @author sadcenter on 26.09.2020
 * @project LASTCORE
 */

public class EnchantCommand extends Command {

    public EnchantCommand() {
        super("eeeeeee", "core.cmd.enchant", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        commandBuilder
                .newArgumentsBuilder()
                .throwError(2, new NotEnoughArgumentsException("enchant [nazwa] [level]"));

        Enchantment enchantment = Enchantment.getByName(args[0].toUpperCase());
        if (enchantment == null) {
            ChatHelper.sendMessage(player, "&cTaki enchant nie istnieje");
            return;
        }
        player.getItemInHand().addUnsafeEnchantment(enchantment, Integer.parseInt(args[1]));
    }
}
