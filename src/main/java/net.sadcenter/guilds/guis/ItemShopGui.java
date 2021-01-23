package net.sadcenter.guilds.guis;

import net.sadcenter.commons.helper.ChatHelper;
import net.sadcenter.commons.helper.ItemBuilder;
import net.sadcenter.commons.helper.ItemHelper;
import net.sadcenter.guilds.LastCore;
import net.sadcenter.guilds.basic.ItemShop;
import net.sadcenter.guilds.managers.ItemShopManager;
import net.sadcenter.guilds.utils.DateUtil;
import net.sadcenter.guilds.utils.ListUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import pl.meme.gui.GuiBuilder;
import pl.meme.gui.GuiItem;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author sadcenter on 16.08.2020
 * @project LASTCORE
 */

public final class ItemShopGui extends GuiBuilder {

    private final CommandSender sender = Bukkit.getConsoleSender();
    private final ItemShopManager itemShopManager = LastCore.getInstance().getItemShopManager();

    public ItemShopGui(@NotNull Player p, @NotNull Set<ItemShop> shopItems) {
        super("&3&LITEMSHOP", InventoryType.CHEST);
        shopItems.forEach(shopItem -> {
            final Set<String> list = ListUtil.replace(shopItem.getLores(), "{DATE}", DateUtil.getDate(shopItem.getDate()));
            addItem(new GuiItem.Builder(ItemBuilder.of(ItemHelper.findMaterial(shopItem.getMaterial()), shopItem.getAmount())
                    .setName(ChatHelper.fixColor(shopItem.getName()))
                    .setLore(ChatHelper.fixColor(new ArrayList<>(list)))
                    .build())
                    .withClickExecutor(e -> {
                        Bukkit.dispatchCommand(sender, shopItem.getCommand());
                        itemShopManager.getBuyed().remove(shopItem);
                        p.closeInventory();
                    }).build());

        });
    }

}
