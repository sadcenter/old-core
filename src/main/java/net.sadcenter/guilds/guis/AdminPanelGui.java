package net.sadcenter.guilds.guis;

import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.storage.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

/**
 * @author sadcenter on 18.10.2020
 * @project LASTCORE
 */

public final class AdminPanelGui extends GuiBuilder {

    public AdminPanelGui(Player p) {
        super("&4&lADMIN-PANEL", 3);


        setItem(10, new GuiItem.Builder(Material.TNT)
                .withItemName("&4&lTNT")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledTnt() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledTnt(!Settings.isEnabledTnt());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(11, new GuiItem.Builder(Material.CHEST)
                .withItemName("&3&LMAGICZNE SKRZYNKI")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledCase() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledCase(!Settings.isEnabledCase());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(12, new GuiItem.Builder(Material.DIAMOND_HELMET)
                .withItemName("&b&lDIAMENTOWE ITEMY")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledDiamondsItems() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledDiamondsItems(!Settings.isEnabledDiamondsItems());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(13, new GuiItem.Builder(Material.ENCHANTMENT_TABLE)
                .withItemName("&3&lENCHANT")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledEnchanting() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledEnchanting(!Settings.isEnabledEnchanting());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(14, new GuiItem.Builder(Material.HAY_BLOCK)
                .withItemName("&3&lITEMY NA GILDIE")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledGuildItems() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledGuildItems(!Settings.isEnabledGuildItems());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(15, new GuiItem.Builder(Material.DRAGON_EGG)
                .withItemName("&3&lZAKLADANIE GILDII")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledGuilds() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledGuilds(!Settings.isEnabledGuilds());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(16, new GuiItem.Builder(Material.DIRT)
                .withItemName("&3&lREGENERACJA GILDII")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledGuildRegeneration() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledGuildRegeneration(!Settings.isEnabledGuildRegeneration());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());

        setItem(9, new GuiItem.Builder(Material.DIAMOND_SWORD)
                .withItemName("&3&LPVP")
                .withLore(Arrays.asList(
                        " ",
                        " &8>> &7Status: " + (Settings.isEnabledPvp() ? "&a&lON" : "&c&lOFF"),
                        " "
                ))
                .withClickExecutor(e -> {
                    Settings.setEnabledPvp(!Settings.isEnabledPvp());
                    p.openInventory(new AdminPanelGui(p).build());
                }).build());


        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 0).build();
        for (int i = 0; i < 27; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }
}
