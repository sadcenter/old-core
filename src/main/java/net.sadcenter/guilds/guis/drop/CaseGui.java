package net.sadcenter.guilds.guis.drop;

import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.managers.CaseManager;
import net.sadcenter.guilds.utils.PolishNamesUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

/**
 * @author sadcenter on 10.10.2020
 * @project LASTCORE
 */

public final class CaseGui extends GuiBuilder {

    public CaseGui() {
        super("&3&lDROP Z MAGICZNYCH SKRZYNEK", 5);
        for (ItemStack drop : CaseManager.getCaseItems()) {
            addItem(new GuiItem.Builder(drop)
                    .withItemName("&3" + PolishNamesUtil.getPolishName(drop.getType(), drop.getDurability()) + " &7x&3" + drop.getAmount())
                    .build());
        }

        Inventory inv = this.build();
        ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 3).build();
        for (int i = 0; i < 45; i++) {
            ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }

        setItem(44, new GuiItem.Builder(Material.FENCE_GATE)
                .withItemName("&c&lWROC DO DROPU")
                .withClickExecutor(e -> e.getWhoClicked().openInventory(new DropMenuGui((Player) e.getWhoClicked()).build()))
                .build());
    }
}
