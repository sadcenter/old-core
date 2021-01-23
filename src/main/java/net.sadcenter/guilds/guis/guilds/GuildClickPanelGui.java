package net.sadcenter.guilds.guis.guilds;

import net.sadcenter.guilds.basic.Guild;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

/**
 * @author sadcenter on 03.11.2020
 * @project LastCore
 */

public final class GuildClickPanelGui extends GuiBuilder {

    public GuildClickPanelGui(Player p, Guild g) {
        super("&5&l[" + g.getTag() + "]", 5);
        setItem(2, 3, new GuiItem.Builder(Material.STAINED_GLASS)
                .withItemData(g.getGlass().getData())
                .withItemName("&6&lAKTUALNY KOLOR: " + g.getGlass().getCustomName())
                .withClickExecutor(e -> e.getWhoClicked().openInventory(new GuildGlassPanelGui(p, g).build())).build()
        );

    }
}
