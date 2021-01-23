package net.sadcenter.guilds.guis;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.basic.fields.KitField;
import net.sadcenter.guilds.storage.Settings;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.UserUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author sadcenter on 05.09.2020
 * @project LASTCORE
 */

public final class KitGui extends GuiBuilder {

    public KitGui(@NotNull Player p) {
        super("&3&LKITY", InventoryType.CHEST);
        KitField user = UserUtil.get(p.getUniqueId()).getKitField();
        long current = System.currentTimeMillis();
        setItem(10,
                new GuiItem.Builder(Material.GRILLED_PORK).
                        withItemName("&7Kit &3jedzenie").
                        withLore(ChatHelper.fixColor(Arrays.asList(" ", " &7Mozesz odebrac za &3" + (user.getFood() > current ? DateUtil.getDurationBreakdownShort(user.getFood() - current) : "juz"), " &7Cooldown &35m", ""))).
                        withClickExecutor(e -> {
                            long curr = System.currentTimeMillis();
                            if (user.getFood() < curr || p.hasPermission("scode.admin")) {
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.GRILLED_PORK, 64), p.getLocation());
                                user.setFood(curr + TimeUnit.MINUTES.toMillis(5));
                                p.closeInventory();
                            } else
                                ChatHelper.sendMessage(p, "&cNie mozesz jeszcze odebrac tego kita!");
                        }).
                        build());

        setItem(12,
                new GuiItem.Builder(Material.LEATHER_HELMET).
                        withItemName("&7Kit &3gracz").
                        withLore(ChatHelper.fixColor(Arrays.asList(" ", " &7Mozesz odebrac za &3" + (user.getPlayer() > current ? DateUtil.getDurationBreakdownShort(user.getPlayer() - current) : "juz"), " &7Cooldown &310m", ""))).
                        withClickExecutor(e -> {
                            long curr = System.currentTimeMillis();
                            if (user.getPlayer() < curr || p.hasPermission("scode.admin")) {
                                Location location = p.getLocation();
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.STONE_PICKAXE), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.ENDER_CHEST), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.GRILLED_PORK, 64), location);
                                //ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.FISHING_ROD, 1).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.WATER_BUCKET, 1).build(), location);
                                user.setPlayer(curr + TimeUnit.MINUTES.toMillis(10));
                                p.closeInventory();
                            } else
                                ChatHelper.sendMessage(p, "&cNie mozesz jeszcze odebrac tego kita!");
                        }).
                        build());

        setItem(14,
                new GuiItem.Builder(Material.GOLD_HELMET).
                        withItemName("&7Kit &3vip").
                        withLore(ChatHelper.fixColor(Arrays.asList(" ", " &7Mozesz odebrac za &3" + (user.getVip() > current ? DateUtil.getDurationBreakdownShort(user.getVip() - current) : "juz"), " &7Cooldown &310h", ""))).
                        withClickExecutor(e -> {
                            if (!p.hasPermission("core.vip")) {
                                ChatHelper.sendMessage(p, "&cNie masz do tego dostepu");
                                return;
                            }
                            long curr = System.currentTimeMillis();
                            if (user.getVip() < curr || p.hasPermission("scode.admin")) {
                                Location location = p.getLocation();
                                if (Settings.isEnabledDiamondsItems()) {
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5).addEnchantment(Enchantment.FIRE_ASPECT, 1).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                } else {
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                }
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.GOLDEN_APPLE, 4, 1).build(), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.GOLDEN_APPLE, 32), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.ENDER_PEARL, 8), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                //ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.FISHING_ROD, 1).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.WATER_BUCKET, 1).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.ARROW, 12).build(), location);
                                //ItemHelper.giveOrDrop(p, new ItemStack(Material.EGG, 16), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.BOW, 1).addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 2).build(), location);
                                user.setVip(curr + TimeUnit.HOURS.toMillis(10));
                                p.closeInventory();
                            } else
                                ChatHelper.sendMessage(p, "&cNie mozesz jeszcze odebrac tego kita!");
                        }).
                        build());

        setItem(16,
                new GuiItem.Builder(Material.DIAMOND_HELMET).
                        withItemName("&7Kit &3svip").
                        withLore(ChatHelper.fixColor(Arrays.asList(" ", " &7Mozesz odebrac za &3" + (user.getSvip() > current ? DateUtil.getDurationBreakdownShort(user.getSvip() - current) : "juz"), " &7Cooldown &310h", ""))).
                        withClickExecutor(e -> {
                            if (!p.hasPermission("core.svip")) {
                                ChatHelper.sendMessage(p, "&cNie masz do tego dostepu");
                                return;
                            }
                            long curr = System.currentTimeMillis();
                            if (user.getSvip() < curr || p.hasPermission("scode.admin")) {
                                Location location = p.getLocation();
                                for (int i = 0; i < 2; i++) {
                                    if (Settings.isEnabledDiamondsItems()) {
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 5).addEnchantment(Enchantment.FIRE_ASPECT, 1).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    } else {
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                        ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                    }
                                }
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.GOLDEN_APPLE, 8, 1).build(), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.GOLDEN_APPLE, 64), location);
                                ItemHelper.giveOrDrop(p, new ItemStack(Material.ENDER_PEARL, 16), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5).addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).addEnchantment(Enchantment.DURABILITY, 3).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.ENDER_PEARL, 16).build(), location);
                          //      ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.FISHING_ROD, 1).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.WATER_BUCKET, 1).build(), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.ARROW, 24).build(), location);
                                //ItemHelper.giveOrDrop(p, new ItemStack(Material.EGG, 32), location);
                                ItemHelper.giveOrDrop(p, ItemBuilder.of(Material.BOW, 1).addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.ARROW_DAMAGE, 2).build(), location);
                                user.setSvip(curr + TimeUnit.HOURS.toMillis(10));
                                p.closeInventory();
                            } else
                                ChatHelper.sendMessage(p, "&cNie mozesz jeszcze odebrac tego kita!");
                        }).
                        build());

        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 3).build();
        for (int i = 0; i < 27; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }

}
