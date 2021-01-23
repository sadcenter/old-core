package net.sadcenter.guilds.runnables;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.basic.fields.DepositField;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author sadcenter on 06.09.2020
 * @project LASTCORE
 */

public final class DepositRunnable implements Runnable {

    public static void checkDeposit(Player onlinePlayer) {
        PlayerInventory inventory = onlinePlayer.getInventory();
        DepositField depositField = UserUtil.get(onlinePlayer.getUniqueId()).getDeposit();
        int totalKox = ItemUtil.getAmount(onlinePlayer, Material.GOLDEN_APPLE, 1);
        if (totalKox > 1) {
            inventory.removeItem(ItemBuilder.of(Material.GOLDEN_APPLE, totalKox - 1, 1).build());
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalKox - 1) + "&7 koxow!");
            depositField.addKox(totalKox - 1);
        }
        int totalRef = ItemUtil.getAmount(onlinePlayer, Material.GOLDEN_APPLE, 0);
        if (totalRef > 12) {
            inventory.removeItem(new ItemStack(Material.GOLDEN_APPLE, totalRef - 12));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka " + (totalRef - 12) + "&7 refilow!");
            depositField.addRefil(totalRef - 12);
        }
        int totalPearls = ItemUtil.getAmount(onlinePlayer, Material.ENDER_PEARL, 0);
        if (totalPearls > 4) {
            inventory.removeItem(new ItemStack(Material.ENDER_PEARL, totalPearls - 4));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalPearls - 4) + "&7 perel!");
            depositField.addPearls(totalPearls - 4);
        }
        int totalEggs = ItemUtil.getAmount(onlinePlayer, Material.EGG, 0);
        if (totalEggs > 8) {
            inventory.removeItem(new ItemStack(Material.EGG, totalEggs - 8));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalEggs - 8) + "&7 jajek!");
            depositField.addEggs(totalEggs - 8);
        }
        int totalArrows = ItemUtil.getAmount(onlinePlayer, Material.ARROW, 0);
        if (totalArrows > 16) {
            inventory.removeItem(new ItemStack(Material.ARROW, totalArrows - 16));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalArrows - 16) + "&7 strzal!");
            depositField.addArrows(totalArrows - 16);
        }
        int totalIce = ItemUtil.getAmount(onlinePlayer, Material.ICE, 0);
        if (totalIce > 0) {
            inventory.removeItem(new ItemStack(Material.ICE, totalIce));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalIce) + "&7 lodu!");
        }
        int totalSnowballs = ItemUtil.getAmount(onlinePlayer, Material.SNOW_BALL, 0);
        if (totalSnowballs > 8) {
            inventory.removeItem(new ItemStack(Material.SNOW_BALL, totalSnowballs - 8));
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalSnowballs - 8) + "&7 sniezek!");
            depositField.addSnows(totalSnowballs - 8);
        }
        int totalPackedIce = ItemUtil.getAmount(onlinePlayer, Material.PACKED_ICE, 0);
        if (totalPackedIce > 0) {
            inventory.remove(Material.PACKED_ICE);
            ChatHelper.sendMessage(onlinePlayer, "&7Odlozono do twojego schowka &3" + (totalPackedIce) + "&7 lodu!");
        }
    }

    @Override
    public void run() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            checkDeposit(onlinePlayer);
        }
    }
}
