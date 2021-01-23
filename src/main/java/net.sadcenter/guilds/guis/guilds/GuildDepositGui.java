package net.sadcenter.guilds.guis.guilds;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;

import static org.apache.commons.lang3.math.NumberUtils.isNumber;

/**
 * @author sadcenter on 18.08.2020
 * @project LASTCORE
 */

public final class GuildDepositGui extends GuiBuilder {


    public GuildDepositGui(Player player, @NotNull Guild g) {
        super(ChatHelper.fixColor("&3&LDEPOZYT"), InventoryType.CHEST);
        ItemBuilder.of(Material.PAPER, 1).setName(ChatHelper.fixColor("&3&lDEPOZYT")).addLore(ChatHelper.fixColor(Arrays.asList("", "&8>> &7Tag gildii: &3" + g.getTag(), "&8>> &7W schowku posiadasz: &3" + g.getDepositBlocks(), ""))).build();
        setItem(12, new GuiItem.Builder(Material.GOLD_BLOCK)
                .withItemName(ChatHelper.fixColor("&3&lWPLAC"))
                .withLore(ChatHelper.fixColor(Arrays.asList("", "&8>> &7Wplac &3" + ItemUtil.getAmount(player, Material.GOLD_BLOCK, 0) + " &7blokow zlota!")))
                .withClickExecutor(e -> {
                    int i = ItemUtil.getAmount(player, Material.GOLD_BLOCK, 0);
                    g.addDeposit(i);
                    player.getInventory().removeItem(new ItemStack(Material.GOLD_BLOCK, i));
                    player.closeInventory();
                    ChatHelper.sendTitle(player, "&8* &3&lDEPOZYT GILDII &8*", "&8>> &7Wplaciles &3" + i + " &7blokow zlota do &3depozytu &7gildii");
                })
                .build());
        setItem(14, new GuiItem.Builder(Material.GOLD_INGOT)
                .withItemName(ChatHelper.fixColor("&3&lWYPLAC"))
                .withLore(ChatHelper.fixColor(Arrays.asList(" ", "&8>> &7Kliknij aby wyplacic &3" + 0 + " &7blokow zlota z &3" + g.getDepositBlocks() + " &7blokow zlota!", "")))
                .withClickExecutor(e -> {
                    player.closeInventory();
                    LastCore.getInstance().getSign().open(player, new String[]{"", "^^^^^^^^^", "Napisz liczbe", "&3&lLAST&F&LCORE&7.PL"}, (player1, lines) -> {
                        if (!isNumber(lines[0])) {
                            ChatHelper.sendMessage(player1, "&cTo nie liczba!");
                            return;
                        }
                        int i = Integer.parseInt(lines[0]);
                        if (g.getDepositBlocks() < i || i <= 0) {
                            ChatHelper.sendMessage(player1, "&cNie posiadasz tyle blokow w depozycie!");
                            return;
                        }
                        player1.closeInventory();
                        g.removeDeposit(i);
                        ItemHelper.giveOrDrop(player1, new ItemStack(Material.GOLD_BLOCK, i), player1.getLocation());
                        ChatHelper.sendTitle(player1, "&3&lDEPOZYT GILDII", "&8>> &7Wyplaciles &3" + i + " &7blokow zlota z &3depozytu &7gildii");
                    });
                })
                .build());

        final Inventory inv = this.build();
        final ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 3).build();
        for (int i = 0; i < 27; i++) {
            final ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }


}
