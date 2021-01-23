package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.command.CommandException;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author sadcenter on 08.11.2020
 * @project LastCore
 */

public class CobblexCommand extends Command {

    public CobblexCommand() {
        super("cx");
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) throws CommandException {
        Player player = (Player) executor;
        if(player.getInventory().contains(Material.COBBLESTONE, 64*9)) {
            player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 69*9));
            player.getInventory().addItem(ItemBuilder.of(Material.MOSSY_COBBLESTONE).addEnchantment(Enchantment.THORNS, 10).setName("&3&LCOBBLEX!").build());
        } else {
            ChatHelper.sendMessage(player, "&cNie posiadasz 64x9 cobbla");
        }
    }
}
