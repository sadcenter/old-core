package net.sadcenter.guilds.guis.guilds;

import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

/**
 * @author sadcenter on 08.11.2020
 * @project LastCore-remake
 */

public final class GuildMembersListGui extends GuiBuilder {

    public GuildMembersListGui(Player p, Guild g) {
        super("&3&LLISTA CZLONKOW!", 5);
        g.getMembers().forEach((s, member) -> {
            GuiItem.Builder builder = new GuiItem.Builder(ItemUtil.getSkull(s))
                    .withItemName("&3&l" + s)
                    .withLore(Arrays.asList(" ", " &8>> &7Data dodania: &3" + DateUtil.getDate(member.getAdded()), ""));

            builder.withClickExecutor(e -> p.openInventory(new GuildPermissionGui(p, member, true).build()));
            addItem(builder.build());
        });
        boolean[] booleans = new boolean[5];
        for (int i = 0; i < 45; i++) {
            int row = i / 9;
            if (getGuiItem(i) != null) {
                booleans[row] = true;
            }
        }
        final ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 3).build();
        for (int i = 0; i < 45; i++) {
            int row = i / 9;
            if (!booleans[row] && getGuiItem(i) == null) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }
}
