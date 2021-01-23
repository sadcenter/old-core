package net.sadcenter.guilds.guis.guilds;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.GuildGlass;
import net.sadcenter.guilds.utils.GuildUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

/**
 * @author sadcenter on 03.11.2020
 * @project LastCore
 */

public final class GuildGlassPanelGui extends GuiBuilder {

    public GuildGlassPanelGui(Player p, Guild g) {
        super("&3&LKOLORY", 2);
        for (GuildGlass value : GuildGlass.values()) {
            addItem(new GuiItem.Builder(Material.STAINED_GLASS)
                    .withItemName(value.getCustomName() + " SZKLO!")
                    .withItemData(value.getData())
                    .build());
        }
        this.click = e -> {
            ItemStack clicked = e.getCurrentItem();
            if (clicked == null) return;
            if (clicked.getItemMeta() == null) return;
            if (clicked.getItemMeta().getDisplayName() == null) return;

            g.setGlass(GuildGlass.find(clicked.getItemMeta().getDisplayName()));
            e.getWhoClicked().closeInventory();
            ChatHelper.sendTitle(p, "", "&aUstawiles inne szklo!");
            GuildUtil.createRoomGuild(g);
        };
    }
}
