package net.sadcenter.guilds.commands.user;

import net.sadcenter.commons.command.Command;
import net.sadcenter.commons.command.CommandBuilder;
import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BlockCommand extends Command {
    public BlockCommand() {
        super("bloki", true);
    }

    @Override
    public void onCommand(CommandSender executor, CommandBuilder commandBuilder, String... args) {
        Player player = (Player) executor;
        Inventory inventory = player.getInventory();
        //lapis
        int lapisBlock = ItemUtil.getAmount(player, Material.getMaterial(351), 1) / 9;
        if (lapisBlock > 0) {
            inventory.removeItem(new ItemStack(Material.getMaterial(351), lapisBlock * 9, (short) 4));
            inventory.addItem(new ItemStack(Material.LAPIS_BLOCK, lapisBlock));
            ChatHelper.sendMessage(player, "&7Wymieniles &3" + lapisBlock * 9 + "&7 lapizu na &3" + lapisBlock + "&7 blokow lapisu!");
        }
        //zloto
        int goldBlock = ItemUtil.getAmount(player, Material.GOLD_INGOT, 1) / 9;
        if (goldBlock > 0) {
            inventory.removeItem(new ItemStack(Material.GOLD_INGOT, goldBlock * 9));
            inventory.addItem(new ItemStack(Material.GOLD_BLOCK, goldBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + goldBlock * 9 + "&7 zlota na &3" + lapisBlock + "&7 blokow zlota!");
        }
        //diamenty
        int diamondBlock = ItemUtil.getAmount(player, Material.DIAMOND, 1) / 9;
        if (diamondBlock > 0) {
            inventory.removeItem(new ItemStack(Material.DIAMOND, diamondBlock * 9));
            inventory.addItem(new ItemStack(Material.DIAMOND_BLOCK, diamondBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + diamondBlock * 9 + "&7 diamentow na &3" + diamondBlock + "&7 blokow diamentow!");
        }
        //emeraldy
        int emeraldBlock = ItemUtil.getAmount(player, Material.EMERALD, 1) / 9;
        if (emeraldBlock > 0) {
            inventory.removeItem(new ItemStack(Material.EMERALD, emeraldBlock * 9));
            inventory.addItem(new ItemStack(Material.EMERALD_BLOCK, emeraldBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + emeraldBlock * 9 + "&7 emeraldow na &3" + emeraldBlock + "&7 blokow emeraldu!");
        }
        //iron
        int ironBlock = ItemUtil.getAmount(player, Material.IRON_INGOT, 1) / 9;
        if (ironBlock > 0) {
            inventory.removeItem(new ItemStack(Material.IRON_INGOT, ironBlock * 9));
            inventory.addItem(new ItemStack(Material.IRON_BLOCK, ironBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + ironBlock * 9 + "&7 zelaza na &3" + ironBlock + "&7 blokow zelaza!");
        }
        //coal
        int coalBlock = ItemUtil.getAmount(player, Material.COAL, 1) / 9;
        if (coalBlock > 0) {
            inventory.removeItem(new ItemStack(Material.COAL, coalBlock * 9));
            inventory.addItem(new ItemStack(Material.COAL_BLOCK, coalBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + coalBlock * 9 + "&7 wegla na &3" + coalBlock + "&7 blokow wegla!");
        }
        //redstone
        int redstoneBlock = ItemUtil.getAmount(player, Material.REDSTONE, 1) / 9;
        if (redstoneBlock > 0) {
            inventory.removeItem(new ItemStack(Material.REDSTONE, redstoneBlock * 9));
            inventory.addItem(new ItemStack(Material.REDSTONE_BLOCK, redstoneBlock));
            ChatHelper.sendMessage(player, "&7Wymienileś &3" + redstoneBlock * 9 + "&7 redstona na &3" + redstoneBlock + "&7 blokow redstona!");
        }


    }

}
