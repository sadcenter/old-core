package net.sadcenter.guilds.guis.guilds;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.guilds.basic.Guild;
import net.sadcenter.guilds.basic.Member;
import net.sadcenter.guilds.basic.fields.BlockField;
import net.sadcenter.guilds.utils.ItemUtil;
import net.sadcenter.guilds.utils.MemberUtil;
import net.sadcenter.guilds.utils.RegenerationUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author sadcenter on 08.11.2020
 * @project LastCore-remake
 */

public final class GuildPanelGui extends GuiBuilder {

    public GuildPanelGui(Player p, Guild g) {
        super("&3&LPANEL", 5);
        setItem(20, new GuiItem.Builder(ItemUtil.getSkull(p.getName()))
                .withItemName("&3&lUPRAWNIENIA")
                .withEnchantment(Enchantment.THORNS, 10)
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zmienic uprawnienia czlonkow!", " "))
                .withClickExecutor(e -> p.openInventory(new GuildMembersListGui(p, g).build()))
                .build());

        setItem(22, new GuiItem.Builder(Material.GOLD_BLOCK)
                .withItemName("&3&LDEPOZYT")
                .withEnchantment(Enchantment.THORNS, 10)
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zobaczyc depozyt gildii!", " "))
                .withClickExecutor(e -> p.openInventory(new GuildDepositGui(p, g).build()))
                .build());

        setItem(24, new GuiItem.Builder(Material.DIRT)
                .withItemName("&3&LREGENERACJA")
                .withEnchantment(Enchantment.THORNS, 10)
                .withLore(Arrays.asList(" ", " &8>> &7Kliknij aby zregenerowac gildie!", " "))
                .withClickExecutor(e -> {
                    Member member = MemberUtil.get(p.getUniqueId());
                    if (!member.isRegenerationPerm()) {
                        ChatHelper.sendMessage(p, "&cNie masz uprawnien do regeneracji!");
                        return;
                    }
                    if (g.getExplodedBlocks().isEmpty()) {
                        ChatHelper.sendMessage(p, "&cNie mozesz zregenerowac 0 blokow!");
                        return;
                    }
                    if (g.isRegenerating()) {
                        ChatHelper.sendMessage(p, "&cRegeneracja juz trwa!");
                        return;
                    }
                    Queue<BlockField> queue = new LinkedBlockingQueue<>(g.getExplodedBlocks());
                    int blocksSize = queue.size() * 32 / 100;

                    if (g.getDepositBlocks() < blocksSize) {
                        ChatHelper.sendMessage(p, "&7Musisz miec &3" + blocksSize + "&7 blokow zlota w depozycie gildii!");
                        return;
                    }
                    g.removeDeposit(blocksSize);
                    g.setRegenerating(true);
                    RegenerationUtil.startRegeneration(g, queue);
                    p.closeInventory();
                })
                .build());

        final Inventory inv = this.build();
        final ItemStack of = ItemBuilder.of(Material.STAINED_GLASS_PANE, 1, 5).build();
        for (int i = 0; i < 45; i++) {
            final ItemStack o = inv.getItem(i);
            if (o == null || o.getType() == Material.AIR) {
                setItem(i, new GuiItem.Builder(of).build());
            }
        }
    }
}
