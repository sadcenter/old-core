package net.sadcenter.guilds.guis.drop;

import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.drop.DropGui;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

/**
 * @author sadcenter on 10.10.2020
 * @project LASTCORE
 */

public final class DropMenuGui extends GuiBuilder {

    public DropMenuGui(Player p) {
        super("&3&lDrop", 5);
        setItem(20, new GuiItem.Builder(Material.MOSSY_COBBLESTONE)
                .withItemName("&3&LCOBBLEX")
                .withEnchantment(Enchantment.THORNS, 10)
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zobaczyc drop z &3&lCOBBLEXA", " "))
                .withClickExecutor(e -> p.openInventory(new CobblexGui().build()))
                .build());

        setItem(22, new GuiItem.Builder(Material.STONE)
                .withItemName("&3&LSTONE")
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zobaczyc drop z &3&LKAMIENIA", " "))
                .withClickExecutor(e -> {
                    e.getWhoClicked().openInventory(new DropGui(p).build());
                })
                .build());

        setItem(24, new GuiItem.Builder(Material.CHEST)
                .withEnchantment(Enchantment.THORNS, 10)
                .withItemName("&3&LMAGICZNE SKRZYNKI")
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zobaczyc drop z &3&LMAGICZNYCH SKRZYNEK", " "))
                .withClickExecutor(e -> p.openInventory(new CaseGui().build()))
                .build());

        setItem(21, new GuiItem.Builder(Material.APPLE)
                .withItemName("&3&lJABLKA")
                .withLore(Arrays.asList(" ", " &8>> &7Drop: &320%", " "))
                .build());

        setItem(23, new GuiItem.Builder(Material.PAPER)
                .withItemName("&3&lINFORMACJE")
                .withLore(Arrays.asList(" ", " &8>> &7", " "))
                .build());

        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 3).build();
        for (int i = 0; i < 45; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }
}
