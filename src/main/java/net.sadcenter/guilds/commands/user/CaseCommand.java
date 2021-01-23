package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.exceptions.impl.NotEnoughArgumentsException;
import net.sadcenter.commons.exceptions.impl.NotNumberException;
import net.sadcenter.commons.exceptions.impl.PlayerNullException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author sadcenter on 15.09.2020
 * @project LASTCORE
 */

public class CaseCommand extends Command {

    public CaseCommand() {
        super("case", "core.cmd.case", false);
    }

    @Override
    public void onCommand(CommandSender player, CommandBuilder commandBuilder, String... args) throws CommandException {
        commandBuilder
                .newArgumentsBuilder()
                .throwError(1, new NotEnoughArgumentsException("/case [nick/all] [ilosc]"))
                .setIf(args, "1", 2, cmdBuilder -> args.length == 1)
                .throwIfNotNumber(new NotNumberException(2), 2);

        int amount = Integer.parseInt(args[1]);
        ItemStack itemStack = ItemBuilder.of(Material.CHEST).setAmount(amount).addEnchantment(Enchantment.THORNS, 10).setName("&3&lMAGICZNA SKRZYNKA!").build();
        if (args[0].equalsIgnoreCase("all")) {
            ChatHelper.sendMessage(player, "&7Nadano &3" + amount + " &7case kazdemu!");
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                ChatHelper.sendTitle(onlinePlayer, "", "&7Caly serwer otrzymal &3" + amount + " &7case!");
                ChatHelper.sendMessage(onlinePlayer, "&7Otrzymales &3" + amount + " &7case");
                onlinePlayer.getInventory().addItem(itemStack);
            }
        } else {
            Player to = commandBuilder
                    .newPlayerBuilder()
                    .getPlayer(1)
                    .throwIf(new PlayerNullException(), playerBuilder -> playerBuilder.getArgs().length == 1 && playerBuilder.beforeBuild() == null)
                    .build();
            ChatHelper.sendMessage(player, "&adone!");
            ChatHelper.sendMessage(to, "&7Otrzymales &3" + amount + " &7case");
            to.getInventory().addItem(itemStack);
        }
    }
}
