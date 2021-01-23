package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author sadcenter on 12.09.2020
 * @project LASTCORE
 */

public class RepairCommand extends Command {

    public RepairCommand() {
        super("repair", "core.cmd.repair", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        if (args.length == 0) {
            final ItemStack toRepair = player.getItemInHand();
            if (!(toRepair.getDurability() == 1 || toRepair.getType().isBlock() || toRepair.getType().isSolid())) {
                toRepair.setDurability((short) 0);
                ChatHelper.sendMessage(player, "&7Naprawiono przedmiot!");
            } else
                ChatHelper.sendMessage(player, "&cNie mozesz naprawic tego przedmiotu!");
        } else if (args[0].equalsIgnoreCase("all")) {
            if (!player.hasPermission("core.repair.all")) {
                ChatHelper.sendMessage(player, "&cNie posiadasz permisji do repair all!");
                return;
            }
            int total = 0;
            for (final ItemStack itemStack : player.getInventory()) {
                if (itemStack == null)
                    continue;

                if (!(itemStack.getDurability() == 1 || itemStack.getType().isBlock() || itemStack.getType().isSolid())) {
                    itemStack.setDurability((short) 0);
                    total++;
                }
            }
            for (final ItemStack itemStack : player.getInventory().getArmorContents()) {
                if (itemStack == null)
                    continue;

                itemStack.setDurability((short) 0);
                total++;

            }
            ChatHelper.sendMessage(player, "&7Naprawiono " + total + " itemow");
        }
    }
}
